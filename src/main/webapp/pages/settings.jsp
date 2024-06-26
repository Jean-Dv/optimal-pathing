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
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full" placeholder="" />
                    </c:if>
                    <c:if test="${empty settings}">
                      <input type="text" id="sourceAddress" name="sourceAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Dirección origen" />
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
            </form>
          </div>
        </div>
      </div>
    </main>
  </body>

