/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Animal;
import model.ClienteAnimal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteAnimalDaoJpa;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;

/**
 *
 * @author evert
 */
public class AdocaoSRV extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");
            String clienteId = request.getParameter("cliente_id");
            String animalId = request.getParameter("animal_id");

            InterfaceDao dao = DaoFactory.novoClienteAnimalDAO();

            InterfaceDao<Cliente> clienteDao = DaoFactory.novoClienteDAO();
            InterfaceDao<Animal> animalDao = DaoFactory.novoAnimalDAO();
            ClienteAnimal adocao = null;

            RequestDispatcher rd = null;

            switch (acao) {
                case "inclusao":
                    Cliente cliente = clienteDao.pesquisarPorId(Integer.parseInt(clienteId));
                    Animal animal = animalDao.pesquisarPorId(Integer.parseInt(animalId));

                    if (cliente != null && animal != null && !animal.getAdotado()) {
                        adocao = new ClienteAnimal(cliente, animal);
                        try {
                            dao.incluir(adocao);
                            animal.setAdotado(true);
                            animalDao.editar(animal);
                        } catch (Exception ex) {
                            System.out.println("Erro: " + ex.getMessage());
                        }
                    }

                    rd = request.getRequestDispatcher("AdocaoSRV?acao=listagem");
                    rd.forward(request, response);

                    break;
                case "exclusao":
                    try {
                        int id = (int) Long.parseLong(request.getParameter("id"));
                        ClienteAnimal clienteAnimal = (ClienteAnimal) dao.pesquisarPorId(id);

                        if (clienteAnimal != null) {

                            Animal animalAdotado = clienteAnimal.getAnimal();
                            animalAdotado.setAdotado(false);
                            animalDao.editar(animalAdotado);

                            dao.excluir(clienteAnimal);
                            request.setAttribute("mensagem", "Adoção excluída com sucesso.");
                        } else {
                            request.setAttribute("mensagem", "Adoção não encontrada.");
                        }
                    } catch (Exception e) {
                        request.setAttribute("mensagem", "Erro ao excluir adoção: " + e.getMessage());
                    }

                    rd = request.getRequestDispatcher("AdocaoSRV?acao=listagem");
                    rd.forward(request, response);
                    break;

                case "listagem":
                    String lista = listagem(); // Gera a lista HTML
                    System.out.println("Lista gerada: " + lista); // Depuração
                    request.setAttribute("lista", lista); // Passa para a JSP
                    rd = request.getRequestDispatcher("ListagemAdocao.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "pesquisar":
                    System.out.println("Entrou no case PESQUISAR");
                    String clienteIdParam = request.getParameter("cliente_id");
                    String animalIdParam = request.getParameter("animal_id");

                    Integer clienteIdInt = (clienteIdParam != null && !clienteIdParam.isEmpty()) ? Integer.valueOf(clienteIdParam) : null;
                    Integer animalIdInt = (animalIdParam != null && !animalIdParam.isEmpty()) ? Integer.valueOf(animalIdParam) : null;

                    ClienteAnimalDaoJpa daoAdocao = new ClienteAnimalDaoJpa();
                    List<ClienteAnimal> resultado = daoAdocao.filtrarPorClienteEAnimal(clienteIdInt, animalIdInt);

                    StringBuilder listaHTML = new StringBuilder();
                    for (ClienteAnimal ca : resultado) {
                        String nomeCliente = (ca.getCliente() != null) ? ca.getCliente().getNome() : "Desconhecido";
                        String nomeAnimal = (ca.getAnimal() != null) ? ca.getAnimal().getNome() : "Desconhecido";

                        listaHTML.append("<tr>")
                                .append("<td>").append(ca.getId()).append("</td>")
                                .append("<td>").append(nomeCliente).append("</td>")
                                .append("<td>").append(nomeAnimal).append("</td>")
                                .append("<td class=\"td-class\">")
                                .append("<form action='AdocaoSRV?acao=exclusao' method='POST' style='display:inline;'>")
                                .append("<input type='hidden' name='id' value='").append(ca.getId()).append("'>")
                                .append("<input type='submit' value='excluir'>")
                                .append("</form>")
                                .append("</td>")
                                .append("</tr>");
                    }
                    String listaHTMLFiltro = listaHTML.toString();
                    request.setAttribute("lista", listaHTMLFiltro);
                    rd = request.getRequestDispatcher("ListagemAdocao.jsp");
                    rd.forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    private String listagem() {
        InterfaceDao dao = new ClienteAnimalDaoJpa();
        List<ClienteAnimal> lista = new ArrayList<>();

        try {
            lista = dao.listar();
        } catch (Exception ex) {
            System.out.println("Erro ao listar adoções: " + ex.getMessage());
        }

        StringBuilder listaHTML = new StringBuilder();

        for (ClienteAnimal clienteA : lista) {
            String nomeCliente = (clienteA.getCliente() != null) ? clienteA.getCliente().getNome() : "Desconhecido";
            String nomeAnimal = (clienteA.getAnimal() != null) ? clienteA.getAnimal().getNome() : "Desconhecido";

            listaHTML.append("<tr>")
                    .append("<td>").append(clienteA.getId()).append("</td>")
                    .append("<td>").append(nomeCliente).append("</td>")
                    .append("<td>").append(nomeAnimal).append("</td>")
                    .append("<td class=\"td-class\">")
                    .append("<form action='AdocaoSRV?acao=exclusao' method='POST' style='display:inline;'>")
                    .append("<input type='hidden' name='id' value='").append(clienteA.getId()).append("'>")
                    .append("<input type='submit' value='excluir'>")
                    .append("</form>")
                    .append("</td>")
                    .append("</tr>");

        }

        return listaHTML.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
