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
import model.Cliente;
import model.dao.ClienteDaoJpa;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;


/**
 *
 * @author evert
 */
public class ClienteSRV extends HttpServlet {

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
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String endereco = request.getParameter("endereco");
            
            InterfaceDao dao = DaoFactory.novoClienteDAO();
            Cliente c;
            RequestDispatcher rd = null;
            
            switch (acao) {
                case "inclusao":
                    
                c = new Cliente (nome, telefone, email, endereco);
                
                try{
                  dao.incluir(c);
                    
                } catch(Exception ex) {
                    System.out.println("Erro: " + ex.getMessage());
                    
                }
                rd = request.getRequestDispatcher("ClienteSRV?acao=listagem");
                rd.forward (request, response);
                
                 break;
                    
                case "exclusao":
                    try {
                        c = new Cliente();
                        c.setId(Integer.parseInt(id));
                        dao.excluir(c);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);  
                    
                    break;
                    
                case "pre-edicao":
                   c = (Cliente) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("usersEditForm.jsp?acao=edicao"
                            + "&id=" + c.getId()
                            + "&nome=" + c.getNome()
                            + "&telefone=" + c.getTelefone()
                            + "&email=" + c.getEmail()
                            + "&endereco=" + c.getEndereco());
                    rd.forward(request, response);
                    break;
                    
                case "edicao":
                    c = new Cliente(nome, telefone, email, endereco);
                    c.setId(Integer.parseInt(id));
                    try {
                        dao.editar(c);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    
                    break;
                    
                case "listagem":
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem());
                    rd.forward(request, response);
                    break;
                    
                   case "pesquisar":
    nome = request.getParameter("nome");
    InterfaceDao daoPesquisa = DaoFactory.novoClienteDAO();
    
    List<Cliente> listaPesquisa;
    if (nome == null || nome.trim().isEmpty()) {
        listaPesquisa = daoPesquisa.listar(); // busca todos se campo estiver vazio
    } else {
        listaPesquisa = daoPesquisa.filtrarPorNome("%" + nome + "%");
    }

    String listaHTMLFiltro = "";
    for (Cliente cliente : listaPesquisa) {
        listaHTMLFiltro = listaHTMLFiltro
                + "<tr>"
                + "<td>" + cliente.getId() + "<td>"
                + "<td>" + cliente.getNome() + "<td>"
                + "<td>" + cliente.getTelefone() + "<td>"
                + "<td>" + cliente.getEmail() + "<td>"
                + "<td>" + cliente.getEndereco() + "<td>"
                + "<td class=\"td-class\">"
                + "<form action=ClienteSRV?acao=pre-edicao method='POST'>"
                + "<input type='hidden' name='id' value=" + cliente.getId() + ">"
                + "<input type='submit' value=editar>"
                + "</form>"
                + "<form action=ClienteSRV?acao=exclusao method='POST'>"
                + "<input type='hidden' name='id' value=" + cliente.getId() + ">"
                + "<input type='submit' value=excluir>"
                + "</form></td>"
                + "<tr>";
    }

    request.setAttribute("lista", listaHTMLFiltro);
    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + java.net.URLEncoder.encode(listaHTMLFiltro, "UTF-8"));
    rd.forward(request, response);
    break;
            }
            
        } catch (Exception ex) {
             System.out.println("Erro: " + ex.getMessage());
        }
    }
    private String listagem() {
        InterfaceDao dao = new ClienteDaoJpa();
        List<Cliente> lista = null;
        try {
            lista = dao.listar();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String listaHTML = " ";
        for (Cliente cliente : lista) {
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + cliente.getId()+ "<td>"
                    + "<td>" + cliente.getNome() + "<td>"
                    + "<td>" + cliente.getTelefone() + "<td>"
                    + "<td>" + cliente.getEmail() + "<td>"
                    + "<td>" + cliente.getEndereco() + "<td>"
                    + "<td class=\"td-class\"><form action=ClienteSRV?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value="
                    + cliente.getId() + "><input type='submit' value=editar>"
                    + "</form>"
                    + "<form action=ClienteSRV?acao=exclusao method='POST'>"
                    + "<input type='hidden' name='id' value="
                    + cliente.getId() + "><input type='submit' value=excluir></td>"
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
