<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ page import="co.edu.uptc.model.Order" %>
<%@ page import="java.util.List"%>
<%@ page import="co.edu.uptc.model.Responsible"%>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
</head>
<body>
  <jsp:include page="../components/navbar.jsp">
    <jsp:param name="section" value="Ordenes" />
    <jsp:param name="hrefSection" value="orders" />
    <jsp:param name="title" value="Editar orden" />
    <jsp:param name="hrefTitle" value="" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>
  <main class="ml-60 max-h-screen p-8 mt-20 sm:overflow-auto sm:ml-16">
    <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
        <div class="lg:col-span-2 lg:py-12">
          <h3 class="text-4xl text-black">Editar orden</h3>
        </div>
        <% Order order = (Order) request.getSession().getAttribute("order"); %>
        <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
          <form action="/project-programation/order" method="post" class="space-y-4">
            <input type="hidden" id="_method" name="_method" value="put" />
            <input type="hidden" id="id" name="id" value="<%= order.getId() %>" />
            <div>
              <label
                for="isCashOn"
                class="flex cursor-pointer items-start gap-4 rounded-lg border border-gray-200 p-4 transition hover:bg-gray-50 has-[:checked]:bg-blue-50"
              >
                <div class="flex items-center">
                  &#8203;
                  <input
                    type="checkbox"
                    class="size-4 rounded border-gray-300" 
                    id="cashonDelivery" <%=order.isCashonDelivery() ? "checked" : "" %>
                    name="cashonDelivery"/>
                </div>
                <div>
                  <strong class="font-medium text-gray-900 sm:text-xs sm:font-sm"> Contra entrega </strong>
                </div>
              </label>
            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto">
                <label
                    for="destinationAddress"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="text"
                      id="destinationAddress"
                      name="destinationAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Dirección de destino"
                      onclick="openModal()"
                      value="${order.destinationAddress}"
                      readonly
                    />
                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Dirección de destino
                    </span>
                  </label>
              </div>
              <div class="flex-auto">
                <label
                    for="descriptionAddress"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="text"
                      id="descriptionAddress"
                      name="descriptionAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 sm:p-3 w-full"
                      placeholder="Descripción de la dirección"
                      value="${order.description}"
                    />
                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Descripción de la dirección
                    </span>
                  </label>
              </div>
            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto">
                <label
                    for="remitterName"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="text"
                      id="remitterName"
                      name="remitterName"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Nombre del remitente"
                      value="${order.remitterName}"
                    />
                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Nombre del remitente
                    </span>
                  </label>
                   <c:if test="${not empty only_letters_are_accepted}">
                     <div class="mt-1 text-xs text-red-500">
                          ${only_letters_are_accepted}
                      </div>
                   </c:if>
              </div>
              <div class="flex-auto">
                <label
                    for="addresseeName"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="text"
                      id="addresseeName"
                      name="addresseeName"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 sm:p-3 w-full"
                      placeholder="Nombre del destinatario"
                      value="${order.addresseeName}"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Nombre del destinatario
                    </span>
                  </label>
                  <c:if test="${not empty only_letters_are_accepted}">
                    <div class="mt-1 text-xs text-red-500">
                         ${only_letters_are_accepted}
                     </div>
                  </c:if>
              </div>
            </div>
                 <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
                    <div class="flex-auto w-full">
                      <label for="price"
                            class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600">
                        <input type="number"
                              id="price"
                              name="price"
                              class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                              placeholder="Precio"
                              value="${order.shippingValue}"
                        />
                        <span class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs">
                         Precio
                        </span>
                      </label>
                    </div>
                    <div class="flex-auto w-full sm:ml-4">
                      <div class="flex gap-4">
                        <div class="w-full sm:w-1/2">
                           <select name="responsible"
                                    id="responsible"
                                    class="block border border-gray-200 shadow-sm w-full focus-within:border-blue-600 focus-within:ring-1 rounded-md text-base bg-transparent p-2"
                            >
                              <option value="">Responsable</option>
                              <% 
                                List<Responsible> responsibles = (List<Responsible>) request.getSession().getAttribute("responsibles"); 
                                for (Responsible responsible : responsibles) { 
                              %>
                              <option value="<%= responsible.getId() %>"<%= responsible.getId().equals(order.getResponsible().getId()) ? "selected" : "" %>>
                                <%= responsible.getName() %>
                              </option>
                              <% } %>
                            </select>
                        </div>
                        <div class="w-full sm:w-1/2">
                          <select name="state"
                                  id="state"
                                  class="block border border-gray-200 shadow-sm w-full focus-within:border-blue-600 focus-within:ring-1 rounded-md bg-transparent border-gray-300 p-2"
                          >
                              <option value="">Estado de orden</option>
                              <option value="Delivered"
                                <%= order.getStatus().getStatus().equals("Delivered") ? "selected" : "" %>
                              >Entregado</option>
                              <option value="Delay"
                                <%= order.getStatus().getStatus().equals("Delay") ? "selected" : "" %>
                              >Demorado</option>
                              <option value="Devolution"
                                <%= order.getStatus().getStatus().equals("Devolution") ? "selected" : "" %>
                              >Devolución</option>
                              <option value="On way"
                                <%= order.getStatus().getStatus().equals("On way") ? "selected" : "" %>
                              >En camino</option>
                              <option value="Warehouse exit"
                                <%= order.getStatus().getStatus().equals("Warehouse exit") ? "selected" : "" %>
                              >Salida del almacén</option>  
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>     
              <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
                Editar
              </button>
                <c:if test="${not empty all_fields_required}">
                  <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center"
                      style="color: red;">
                      ${all_fields_required}
                  </div>
                </c:if>
            </div>
            <c:if test="${not empty errorMessageGoogleMaps}">
              <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center"
                style="color: red;">
                ${errorMessageGoogleMaps}
              </div>
            </c:if>
            <div id="addressModal" class="${not empty errorMessageAddress ? '' : 'hidden'} fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
                <div class="bg-white rounded-lg shadow-lg p-6 w-full max-w-4xl">
                  <h2 class="text-2xl text-black mb-4">Dirección de Destino</h2>
                  <form id="addressForm" action="/project-programation/order" method="post" class="space-y-4">
                    <div class="flex space-x-4">
                      <div class="w-1/3">
                        <label for="streetType" class="block text-sm font-medium text-gray-700">Tipo de Calle</label>
                        <select id="streetType" name="streetType" class="mt-1 p-2 border border-gray-300 rounded-md w-full" oninput="updateDestinationAddress()">
                          <option value="">Tipo de Calle</option>
                          <option value="Av">Avenida</option>
                          <option value="Av Cl">Avenida calle</option>
                          <option value="Av Cra">Avenida carrera</option>
                          <option value="Cl">Calle</option>
                          <option value="Cra">Carrera</option>
                          <option value="Circ">Circular</option>
                          <option value="Circ">Circunvalar</option>
                          <option value="Dg">Diagonal</option>
                          <option value="Mz">Manzana</option>
                          <option value="Tv">Transversal</option>
                          <option value="Vía">Vía</option>
                        </select>
                      </div>
                      <div class="w-1/3">
                        <label for="streetName" class="block text-sm font-medium text-gray-700">Nombre de calle</label>
                        <input type="text" id="streetName" name="streetName" class="mt-1 p-2 border border-gray-300 rounded-md w-full" placeholder="Nombre" oninput="updateDestinationAddress()">
                        <p class="text-sm text-gray-500 mt-1">Completa solo con el nombre y el prefijo. Ej: 22 Sur.</p>
                      </div>
                      <div class="w-1/6">
                        <label for="number" class="block text-sm font-medium text-gray-700">Número</label>
                        <input type="number" id="number" name="number" class="mt-1 p-2 border border-gray-300 rounded-md w-full" placeholder="#" oninput="updateDestinationAddress()">
                      </div>
                      <div class="w-1/6">
                        <label for="suffix" class="block text-sm font-medium text-gray-700">-</label>
                        <input type="number" id="suffix" name="suffix" class="mt-1 p-2 border border-gray-300 rounded-md w-full" placeholder="-" oninput="updateDestinationAddress()">
                      </div>
                    </div>
                    <div class="flex justify-end space-x-4">
                      <button
                        type="button"
                        class="bg-black text-white px-4 py-2 rounded-md"
                        onclick="saveAddress()"
                      >
                      Guardar
                      </button>
                      <button 
                        type="button"
                        class="bg-black text-white px-4 py-2 rounded-md" 
                        onclick="closeModal()"
                      >
                      Cerrar
                      </button>
                    </div>
                    <c:if test="${not empty errorMessageAddress}">
                      <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center"
                        style="color: red;"
                        onclick="openModal()">
                        ${errorMessageAddress}
                      </div>
                    </c:if>
                  </form>
                </div>
              </div>
          </form>
        </div>
      </div>
    </div>
    <script>
    function openModal() {
      document.getElementById('addressModal').classList.remove('hidden');
    }

    function closeModal() {
    document.getElementById('addressModal').classList.add('hidden');
    }

    function updateDestinationAddress() {
    const streetType = document.getElementById('streetType').value;
    const streetName = document.getElementById('streetName').value;
    const number = document.getElementById('number').value;
    const suffix = document.getElementById('suffix').value;

    const destinationAddress = `${streetType} ${streetName} ${number}-${suffix}`;
    document.getElementById('destinationAddress').value = destinationAddress;
   }

    function saveAddress() {
        const streetType = document.getElementById('streetType').value;
        const streetName = document.getElementById('streetName').value;
        const number = document.getElementById('number').value;
        const suffix = document.getElementById('suffix').value;
        let fullAddress = '';

        if (streetType) fullAddress += streetType + ' ';
        if (streetName) fullAddress += streetName + ' ';
        if (number) fullAddress += ' ' + number;
        if (suffix) fullAddress += '-' + suffix;

        document.getElementById('destinationAddress').value =fullAddress.trim();

        closeModal()
      }
      </script>
  </main>
</body>

