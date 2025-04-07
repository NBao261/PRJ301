package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import dto.HouseDTO;
import dto.UserDTO;

public final class houseList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>House List</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <h1>\n");
      out.write("            Welcome ");
      out.print(user.getName());
      out.write("\n");
      out.write("        </h1>\n");
      out.write("\n");
      out.write("        <a href=\"MainController?action=logout\"> Logout </a>\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        <form action=\"MainController\" method=\"POST\">\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"search\"/>\n");
      out.write("            Search By Name <input type=\"text\" name=\"searchName\" value=\"");
      out.print(request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : "");
      out.write("\"/>\n");
      out.write("            <input type=\"submit\" value=\"Search\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        <table border = \"1\">\n");
      out.write("            <tr>\n");
      out.write("                <th>no</th>\n");
      out.write("                <th>id</th>\n");
      out.write("                <th>name</th>\n");
      out.write("                <th>description</th>\n");
      out.write("                <th>price</th>\n");
      out.write("                <th>size</th>\n");
      out.write("                <th>status</th>\n");
      out.write("                <th>action</th>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            ");

                int count = 0;
                List<HouseDTO> house = (List<HouseDTO>) request.getAttribute("house");
                if (house != null) {
                    for (HouseDTO houses : house) {
                        count++;
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(count);
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getId());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getName());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getDescription());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getPrice());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getSize());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(houses.getStatus());
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                    <a href=\"MainController?action=delete&id=");
      out.print( houses.getId());
      out.write("&searchName=");
      out.print(request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : "");
      out.write("\">\n");
      out.write("                        <button>Delete</button>\n");
      out.write("                    </a>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");

                    }
                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
