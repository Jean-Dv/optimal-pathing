<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="co.edu.uptc.model.Order" %>
<%@ page import="java.util.HashMap" %>
<% HashMap<String, String> statusMap = new HashMap<String, String>();
   statusMap.put("WAREHOUSE_EXIT", "Salida del almacén");
   statusMap.put("ON_WAY", "En camino");
   statusMap.put("DEVOLUTION", "Devolución");
   statusMap.put("DELIVERED", "Entregado");
   statusMap.put("DELAY", "Demorado");
%>
<html lang="en">
<head>
    <title>Inicio</title>
    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
</head>
<body>
  <jsp:include page="../components/navbar.jsp">
    <jsp:param name="section" value="Ordenes" />
    <jsp:param name="hrefSection" value="orders" />
    <jsp:param name="title" value="Inicio" />
    <jsp:param name="hrefTitle" value="orders" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>

  <main class="ml-60 max-h-screen overflow-auto p-8 mt-20 sm:ml-16">
              <form id="filterForm" method="get" action="/project-programation/orders">
                      <div class="flex justify-between items-start gap-8">
                  <div class="flex gap-8">
                    <div class="relative">
                      <details class="group [&_summary::-webkit-details-marker]:hidden">
                        <summary class="flex cursor-pointer items-center gap-2 border-b border-gray-400 pb-1 text-gray-900 transition hover:border-gray-600">
                          <span class="text-sm font-medium"> Estado </span>
                          <span class="transition group-open:-rotate-180">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-4 w-4">
                              <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                            </svg>
                          </span>
                        </summary>
                        <div class="z-50 group-open:absolute group-open:start-0 group-open:top-auto ">
                          <div class="w-96 rounded border border-gray-200 bg-white">
                          <header class="flex items-center justify-between p-4">
                                <span class="text-sm text-gray-700">Filtro Por Estado</span>
                                <button type="button" class="text-sm text-gray-900 underline underline-offset-4" onclick="redirectToOrders()">Restablecer</button>
                            </header>
                            <ul class="space-y-1 border-t border-gray-200 p-4">
                              <li>
                                <label for="FilterPreOrder" class="inline-flex items-center gap-2">
                                  <input href="" type="checkbox" id="FilterInStock" class="size-5 rounded border-gray-300" name="status" value="Warehouse exit" onchange="filterOrders()"/>
                                  <span class="text-sm font-medium text-gray-700"> Salida del almacén </span>
                                </label>
                              </li>
                              <li>
                                <label for="FilterPreOrder" class="inline-flex items-center gap-2">
                                  <input type="checkbox" id="FilterPreOrder" class="size-5 rounded border-gray-300" name="status" value="On way" onchange="filterOrders()" />
                                  <span class="text-sm font-medium text-gray-700"> En camino  </span>
                                </label>
                              </li>
                              <li>
                                <label for="FilterPreOrder" class="inline-flex items-center gap-2">
                                  <input type="checkbox" id="FilterPreOrder" class="size-5 rounded border-gray-300" name="status" value="DEVOLUTION" onchange="filterOrders()" />
                                  <span class="text-sm font-medium text-gray-700"> Devolución  </span>
                                </label>
                              </li>
                              <li>
                                <label for="FilterOutOfStock" class="inline-flex items-center gap-2">
                                  <input type="checkbox" id="FilterOutOfStock" class="size-5 rounded border-gray-300" name="status" value="DELIVERED" onchange="filterOrders()"/>
                                  <span class="text-sm font-medium text-gray-700"> Entregado  </span>
                                </label>
                              </li>
                              <li>
                                <label for="FilterPreOrder" class="inline-flex items-center gap-2">
                                  <input type="checkbox" id="FilterPreOrder" class="size-5 rounded border-gray-300" name="status" value="DELAY" onchange="filterOrders()" />
                                  <span class="text-sm font-medium text-gray-700"> Demorado  </span>
                                </label>
                              </li>
                            </ul>   
                          </div>
                        </div>
                      </details>
                    </div>
                  </div>
            </form>

            <div class="inline-flex rounded-lg border border-gray-100 bg-gray-100 p-1 mb-4">
              <button type="button" class="inline-flex items-center gap-2 rounded-md px-4 py-2 text-sm text-gray-500 hover:text-gray-700 focus:relative">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M6 8C6 7.44772 6.44772 7 7 7H17C17.5523 7 18 7.44772 18 8C18 8.55228 17.5523 9 17 9H7C6.44772 9 6 8.55228 6 8Z" fill="currentColor" /><path d="M8 12C8 11.4477 8.44772 11 9 11H15C15.5523 11 16 11.4477 16 12C16 12.5523 15.5523 13 15 13H9C8.44772 13 8 12.5523 8 12Z" fill="currentColor" /><path d="M11 15C10.4477 15 10 15.4477 10 16C10 16.5523 10.4477 17 11 17H13C13.5523 17 14 16.5523 14 16C14 15.4477 13.5523 15 13 15H11Z" fill="currentColor" /></svg>
                Ordenar
              </button>
              <button type="button" class="inline-flex items-center gap-2 rounded-md bg-white px-4 py-2 text-sm text-gray-500 hover:text-gray-700 focus:relative" onclick="window.location.href='/project-programation/order'">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path fill-rule="evenodd" clip-rule="evenodd" d="M2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22C6.47715 22 2 17.5228 2 12ZM12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4Z" fill="currentColor" />
                  <path fill-rule="evenodd" clip-rule="evenodd" d="M13 7C13 6.44772 12.5523 6 12 6C11.4477 6 11 6.44772 11 7V11H7C6.44772 11 6 11.4477 6 12C6 12.5523 6.44772 13 7 13H11V17C11 17.5523 11.4477 18 12 18C12.5523 18 13 17.5523 13 17V13H17C17.5523 13 18 12.5523 18 12C18 11.4477 17.5523 11 17 11H13V7Z" fill="currentColor" />
                </svg>
                Añadir
              </button>
            </div>
          </div>

    <div class="overflow-x-auto rounded-lg border border-gray-200">
      <table class="min-w-full divide-y-2 divide-gray-200 bg-white text-sm">
        <thead class="text-left">
          <tr>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Orden</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Nombre del Remitente</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Estado</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Dirección de Destino</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Nombre del Destionatario</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Pago contra Entrega</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Acciones</th>
          </tr>
        </thead>
        <tbody id="ordersBody" class="divide-y divide-gray-200">
          <% List<Order> orders = (List<Order>) request.getSession().getAttribute("orders"); %>
          <% if (orders.isEmpty()) { %>
            <tr>
              <td colspan="7" class="whitespace-nowrap px-4 py-2 text-gray-700 text-center">No hay ordenes</td>
            </tr>
          <% } %>
          <% for (Order order : orders) { %>
            <tr id="<%= order.getId() %>">
              <td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900"><%= order.getId() %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700"><%= order.getRemitterName() %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700"><%= statusMap.get(order.getStatus().toString()) %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700"><%= order.getDestinationAddress() %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700"><%= order.getAddresseeName() %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700"><%= order.isCashonDelivery() ? "✔️" : "❌" %></td>
              <td class="whitespace-nowrap px-4 py-2 text-gray-700">
                <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                 <button
                        class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                        title="Editar estado de la orden"
                          onclick="window.location.href='/project-programation/order?id=<%= order.getId() %>&action=editstate'">
                    <svg 
                      xmlns="http://www.w3.org/2000/svg" 
                      viewBox="0 0 24 24" 
                      fill="none" 
                      stroke="currentColor" 
                      stroke-width="2" 
                      stroke-linecap="round" 
                      stroke-linejoin="round" 
                      class="h-4 w-4"
                      >
                      <path d="M12 3c-4.97 0-9 4.03-9 9s4.03 9 9 9 9-4.03 9-9-4.03-9-9-9zm0 2c2.76 0 5 2.24 5 5s-2.24 5-5 5-5-2.24-5-5 2.24-5 5-5z"></path>
                      <circle cx="12" cy="12" r="3"></circle>
                    </svg>

                  </button>
                  <button
                    class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                    title="Editar orden"
                      onclick="window.location.href='/project-programation/order?id=<%= order.getId() %>&action=edit'">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      stroke="currentColor"
                      class="h-4 w-4"
                    >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"
                    />
                    </svg>
                  </button>
                  <button
                    class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                    title="Eliminar orden"
                    onclick="<%="showAlert('" + order.getId() + "')"%>"
                    >
                    <svg xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke-width="1.5"
                      width="18" height="18"
                    >
                      <path fill-rule="evenodd" 
                        d="M17 5V4C17 2.89543 16.1046 2 15 2H9C7.89543 2 7 2.89543 7 4V5H4C3.44772 5 3 5.44772 3 6C3 6.55228 3.44772 7 4 7H5V18C5 19.6569 6.34315 21 8 21H16C17.6569 21 19 19.6569 19 18V7H20C20.5523 7 21 6.55228 21 6C21 5.44772 20.5523 5 20 5H17ZM15 4H9V5H15V4ZM17 7H7V18C7 18.5523 7.44772 19 8 19H16C16.5523 19 17 18.5523 17 18V7Z"
                        fill="currentColor" />
                      <path d="M9 9H11V17H9V9Z" fill="currentColor"   />
                      <path d="M13 9H15V17H13V9Z" fill="currentColor" />
                    </svg>
                  </button>
                </span>
              </td>
            </tr>
          <% } %>
        </tbody>
      </table>
    </div>
  </main>

  <div id="warning-message" class="alert-wrapper fixed inset-0 flex items-center justify-center z-50 bg-gray-800 bg-opacity-50">
    <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
        <div
          class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12 sm:p-6 lg:p-8 flex flex-col justify-center items-center"
          role="alert">
          <svg width="100" height="100" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M12 6C12.5523 6 13 6.44772 13 7V13C13 13.5523 12.5523 14 12 14C11.4477 14 11 13.5523 11 13V7C11 6.44772 11.4477 6 12 6Z"
              fill="currentColor" />
            <path
              d="M12 16C11.4477 16 11 16.4477 11 17C11 17.5523 11.4477 18 12 18C12.5523 18 13 17.5523 13 17C13 16.4477 12.5523 16 12 16Z"
              fill="currentColor" />
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2ZM4 12C4 16.4183 7.58172 20 12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4C7.58172 4 4 7.58172 4 12Z"
              fill="currentColor" />
          </svg>

          <h1 class="text-center"> ¿Estás segur@?</h1>
          <p class="mt-4 text-gray-500 text-center">
            ¡No podrás revertir esto!
          </p>

          <div class="mt-6 sm:flex sm:gap-4 justify-center">
            <div class="mt-6">
              <button
              class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              onclick="deleteOrder()"
            >
              ¡Sí, bórralo!
            </button>
            </div>
            <div class="mt-6">
              <button
              class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              onclick="hideWarning()"
            >
              Cancelar
            </button>
            </div>
          </div>
        </div>
      </div>
  </div>


<% int currentPage =(Integer) request.getSession().getAttribute("currentPage");
      int totalPages =(Integer) request.getSession().getAttribute("totalPages");     
  %>
  
        <ol class="flex justify-center gap-1 text-xs font-medium ml-56">
            <li>
                <a href="?page=<%= currentPage > 1 ? currentPage - 1 : 1 %>&status=<%= request.getParameter("status") %>"
                   class="inline-flex size-8 items-center justify-center rounded border border-gray-100 bg-white text-gray-900 rtl:rotate-180">
                    <span class="sr-only">Prev Page</span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3" viewBox="0 0 20 20" fill="currentColor">
                        <path fill-rule="evenodd"
                              d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                              clip-rule="evenodd"/>
                    </svg>
                </a>
            </li>

            <% for (int i = 1; i <= totalPages; i++) { %>
                <li>
                    <a href="?page=<%= i %>&status=<%= request.getParameter("status") %>"
                       class="block size-8 rounded border text-center leading-8
                              <%= currentPage == i ? "bg-blue-500 text-white border-blue-500" : "bg-white text-gray-900 border-blue-100" %>">
                        <%= i %>
                    </a>
                </li>
            <% } %>

            <li>
                <a href="?page=<%= currentPage < totalPages ? currentPage + 1 : totalPages %>&status=<%= request.getParameter("status") %>"
                   class="inline-flex size-8 items-center justify-center rounded border border-gray-100 bg-white text-gray-900 rtl:rotate-180">
                    <span class="sr-only">Next Page</span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3" viewBox="0 0 20 20" fill="currentColor">
                        <path fill-rule="evenodd"
                              d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                              clip-rule="evenodd"/>
                    </svg>
                </a>
            </li>
        </ol>

</body>

<script>
  function showAlert(id) {
    document.getElementById('warning-message').style.display = 'flex';
    localStorage.setItem('idToDelete', id);
  }

  function hideWarning() {
    document.getElementById('warning-message').style.display = 'none';
    localStorage.removeItem('idToDelete');
  }

  function deleteOrder() {
    const id = localStorage.getItem('idToDelete');
    const url = "/project-programation/order?id=" + id;
    fetch(url, {
      method: 'DELETE',
    })
    .then(data => {
      document.getElementById(id).remove();
      const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.onmouseenter = Swal.stopTimer;
          toast.onmouseleave = Swal.resumeTimer;
        }
      });
      Toast.fire({
        icon: "success",
        title: "Eliminado correctamente"
      });
      hideWarning();
    })
    .catch((error) => {
      console.log(error);
      const Toast = Swal.mixin({
          toast: true,
          position: "top-end",
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
          }
        });
      Toast.fire({
        icon: "error",
        title: "Something went wrong!, Contact with the administrator"
      });
      hideWarning();
    });
  }


 function filterOrders() {
           document.getElementById("filterForm").submit();
 }

 function redirectToOrders() {
        window.location.href = "/project-programation/orders";  
    }
</script>

