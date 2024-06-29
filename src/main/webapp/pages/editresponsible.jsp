<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ page import="co.edu.uptc.model.Responsible" %>
<html lang="en">
<head>
    <title>Inicio</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
</head>
<body>
  <jsp:include page="../components/navbar.jsp">
    <jsp:param name="section" value="Responsable" />
    <jsp:param name="hrefSection" value="responsibles" />
    <jsp:param name="title" value="Editar responsable" />
    <jsp:param name="hrefTitle" value="" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>
  <main class="ml-60 max-h-screen p-8 mt-20 sm:overflow-auto sm:ml-16">
    <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
        <div class="lg:col-span-2 lg:py-12">
          <h3 class="text-4xl text-black">Editar responsable</h3>
        </div>
        <% Responsible responsible = (Responsible) request.getSession().getAttribute("responsible"); %>
        <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
        <form action="/project-programation/responsible" method="post" class="space-y-4" >
          <input type="hidden" id="_method" name="_method" value="put" />
          <input type="hidden" id="id" name="id" value="<%= responsible.getId() %>" />
          <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
                <div class="flex-auto">
                  <label
                      for="name"
                      name="name"
                      class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-black focus-within:ring-1 focus-within:ring-black"
                      >
                      <input
                        type="text"
                        id="name"
                        name="name"
                        class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                        placeholder="Name"
                        value="${responsible.name}"
                      />
                      <span
                        class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                      >
                      Nombre
                      </span>
                    </label>
                    <c:if test="${not empty errorMessageName}">
                            <div class="mt-1 text-xs text-red-500">
                              ${errorMessageName}
                            </div>
                    </c:if>
                </div>
                <div class="flex-auto">
                  <label
                      for="email"
                      name="email"
                      class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-black focus-within:ring-1 focus-within:ring-black"
                    >
                      <input
                        type="text"
                        id="email"
                        name="email"
                        class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                        placeholder="Email address"
                        value="${responsible.email}"
                      />

                      <span
                        class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                      >
                      Correo Electrónico
                      </span>
                  </label>
                    <c:if test="${not empty errorMessageEmail}">
                            <div class="mt-1 text-xs text-red-500">
                              ${errorMessageEmail}
                            </div>
                    </c:if>
                </div>
                <div class="flex-auto">
                  <label
                      for="numberIdentification"
                      name="numberIdentification"
                      class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-black focus-within:ring-1 focus-within:ring-black"
                    >
                      <input
                      type="text"
                      id="numberIdentification"
                      name="numberIdentification"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Cédula de ciudadanía"
                      value="${responsible.numberIdentification}"
                      />

                      <span
                        class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                      >
                      Cédula de ciudadanía
                      </span>
                  </label>
                    <c:if test="${not empty errorMessagenumberIdentification}">
                            <div class="mt-1 text-xs text-red-500">
                              ${errorMessagenumberIdentification}
                            </div>
                    </c:if>
                </div>
                <div class="flex-auto">
                  <label
                      for="phone"
                      name="phone"
                      class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-black focus-within:ring-1 focus-within:ring-black"
                    >
                      <input
                        type="tel"
                        id="phone"
                        name="phone"
                        class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                        placeholder="Phone Number"
                        value="${responsible.phone}"
                      />

                      <span
                        class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                      >
                      Teléfono
                      </span>
                  </label>
                    <c:if test="${not empty errorMessagephone}">
                            <div class="mt-1 text-xs text-red-500">
                              ${errorMessagephone}
                            </div>
                    </c:if>
                </div>
            </div>
            <div class="mt-4">
              <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
                Enviar
              </button>
            </div>
            <c:if test="${not empty errorMessage}">
              <div class="mt-1 text-xs text-red-500">
                ${errorMessage}
              </div>
            </c:if>
          </form>
        </div>
      </div>
    </div>
  </main>
</body>

