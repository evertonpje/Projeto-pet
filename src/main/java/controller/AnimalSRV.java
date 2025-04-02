/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Animal;
import model.Cliente;
import model.dao.AnimalDaoJpa;
import model.dao.ClienteDaoJpa;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;

/**
 *
 * @author evert
 */
public class AnimalSRV extends HttpServlet {

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
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String especie = request.getParameter("especie");
            String raca = request.getParameter("raca");
            
            
            
            InterfaceDao dao = DaoFactory.novoAnimalDAO();
            Animal c;
            
            RequestDispatcher rd = null;
            
            switch (acao) {
                case "inclusao":
                    
                c = new Animal (nome, especie, raca);
                
                try{
                  dao.incluir(c);
                    
                } catch(Exception ex) {
                    System.out.println("Erro: " + ex.getMessage());
                    
                }
                rd = request.getRequestDispatcher("ListagemAnimal.jsp");
                rd.forward (request, response);
                
                 break;
                    
                case "exclusao":
                    try {
                        c = new Animal();
                        c.setId(Integer.parseInt(id));
                        dao.excluir(c);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    rd = request.getRequestDispatcher("ListagemAnimal.jsp?lista=" + listagem());
                    rd.forward(request, response);  
                    
                    break;
                    
                case "pre-edicao":
                   c = (Animal) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("petsEditForm.jsp?acao=edicao"
                            + "&id=" + c.getId()
                            + "&nome=" + c.getNome()
                            + "&especie=" + c.getEspecie()
                            + "&raca=" + c.getRaca());
                    rd.forward(request, response);
                    break;
                    
                case "edicao":
                    c = new Animal(nome, especie, raca);
                    c.setId(Integer.parseInt(id));
                    try {
                        dao.editar(c);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    rd = request.getRequestDispatcher("ListagemAnimal.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    
                    break;
                    
                case "listagem":
                    rd = request.getRequestDispatcher("ListagemAnimal.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
                    
            }
        } catch (Exception ex) {
             System.out.println("Erro: " + ex.getMessage());
        }
    }
    private String listagem() {
        InterfaceDao dao = new AnimalDaoJpa();
        List<Animal> lista = null;
        try {
            lista = dao.listar();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String listaHTML = " ";
        for (Animal animal : lista) {
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + animal.getNome() + "<td>"
                    + "<td>" + animal.getEspecie()+ "<td>"
                    + "<td>" + animal.getRaca()+ "<td>"
                    + "<td><form action=AnimalSRV?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value="
                    + animal.getId() + "><input type='submit' value=editar>"
                    + "</form></td>"
                    + "<form action=AnimalSRV?acao=exclusao method='POST'>"
                    + "<td><input type='hidden' name='id' value="
                    + animal.getId() + "><input type='submit' value=excluir></td>"
                    + "</form>"
                    + "<tr>";
        }

        return listaHTML;
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
