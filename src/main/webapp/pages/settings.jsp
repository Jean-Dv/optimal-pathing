<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <html lang="en">

  <head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
  </head>

  <body>
    <jsp:include page="../components/navbar.jsp">
      <jsp:param name="section" value="Inicio" />
      <jsp:param name="hrefSection" value="orders" />
      <jsp:param name="title" value="Configuraciones" />
      <jsp:param name="hrefTitle" value="settings" />
    </jsp:include>
    <%@ include file="../components/sidemenu.jsp" %>

    <main class="ml-60 max-h-screen p-8 mt-20 sm:overflow-auto sm:ml-16">
      <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
        <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
          <div class="lg:col-span-2 lg:py-12">
            <h3 class="text-4xl text-black">Configuraciones</h3>
            <c:if test="${not empty successfully}">
              <div class="mt-1 text-sm text-green-500">
                  ${successfully}
              </div>
            </c:if>
          </div>

          <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
            <form action="/project-programation/settings" method="post" class="space-y-4">
              <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
                <div class="flex-auto">
                  <label for="sourceAddress" name="sourceAddress"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600">
                    <c:if test="${not empty settings}">
                      <input type="text" id="sourceAddress" name="sourceAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full" placeholder="" onclick="openModal()"
                      readonly />
                    </c:if>
                    <c:if test="${empty settings}">
                      <input type="text" id="sourceAddress" name="sourceAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Dirección origen"
                      onclick="openModal()"
                      readonly
                       />
                    </c:if>
                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs">
                        <c:if test="${not empty settings}">
                          Dirección de origen: ${settings.getSourceAddress()}
                        </c:if>
                        <c:if test="${empty settings}">
                          Dirección de origen
                        </c:if>
                    </span>
                  </label>
                  <c:if test="${not empty source_address_is_required}">
                     <div class="mt-1 text-xs text-red-500" id="source_address_is_required">
                          ${source_address_is_required}
                      </div>
                   </c:if>
                </div>
              </div>
              <div class="mt-6">
                <button type="submit"
                      class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto">
                  <c:if test="${not empty settings}">
                    Actualizar
                  </c:if>
                  <c:if test="${empty settings}">
                    Envíar
                  </c:if>
                </button>
              </div>
              <c:if test="${not empty intern_error}">
                <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center text-red-500">
                  ${intern_error}
                </div>
              </c:if>
              <div id="addressModal"
                class="${not empty errorMessageAddress ? '' : 'hidden'} fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
                <div class="bg-white rounded-lg shadow-lg p-6 w-full max-w-4xl">
                  <h2 class="text-2xl text-black mb-4">Dirección de origen</h2>
                  <form id="addressForm" class="space-y-4">
                    <div class="flex space-x-4">
                      <div class="w-1/3">
                        <label for="streetType" class="block text-sm font-medium text-gray-700">Tipo de Calle</label>
                        <select id="streetType" name="streetType" class="mt-1 p-2 border border-gray-300 rounded-md w-full">
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
                        <input type="text" id="streetName" name="streetName"
                          class="mt-1 p-2 border border-gray-300 rounded-md w-full" placeholder="Nombre">
                        <p class="text-sm text-gray-500 mt-1">Completa solo con el nombre y el prefijo. Ej: 22 Sur.</p>
                      </div>
                      <div class="w-1/6">
                        <label for="number" class="block text-sm font-medium text-gray-700">Número</label>
                        <input type="number" id="number" name="number" class="mt-1 p-2 border border-gray-300 rounded-md w-full"
                          placeholder="#">
                      </div>
                      <div class="w-1/6">
                        <label for="suffix" class="block text-sm font-medium text-gray-700">-</label>
                        <input type="number" id="suffix" name="suffix" class="mt-1 p-2 border border-gray-300 rounded-md w-full"
                          placeholder="-">
                      </div>
                    </div>
                    <div class="flex justify-end space-x-4">
                      <button type="button" class="bg-black text-white px-4 py-2 rounded-md" onclick="saveAddress()">
                        Guardar
                      </button>
                      <button type="button" class="bg-black text-white px-4 py-2 rounded-md" onclick="closeModal()">
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
          function saveAddress() {
            const streetType = document.getElementById('streetType').value;
            const streetName = document.getElementById('streetName').value;
            const number = document.getElementById('number').value;
            const suffix = document.getElementById('suffix').value;

            let fullAddress = '';

            if (streetType) fullAddress += streetType + ' ';
            if (streetName) fullAddress += streetName + ' ';
            if (number) fullAddress += '#' + number;
            if (suffix) fullAddress += '-' + suffix;

            document.getElementById('sourceAddress').value = fullAddress.trim();

            closeModal();
            }
        </script>
    </main>
  </body>

