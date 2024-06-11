<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
</head>
<body>
  <jsp:include page="../components/navbar.jsp">
    <jsp:param name="section" value="Ordenes" />
    <jsp:param name="hrefSection" value="orders.jsp" />
    <jsp:param name="title" value="Add order" />
    <jsp:param name="hrefTitle" value="addorder.jsp" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>

<main class="ml-60 max-h-screen p-8 mt-20 sm:overflow-auto sm:ml-16">
    <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
        <div class="lg:col-span-2 lg:py-12">
          <h3 class="text-4xl text-black">Add order</h3>
        </div>

        <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
          <form action="/project-programation/order" method="post" class="space-y-4" >
            <div>
              <label
                class="flex cursor-pointer items-start gap-4 rounded-lg border border-gray-200 p-4 transition hover:bg-gray-50 has-[:checked]:bg-blue-50"
              >
                <div class="flex items-center">
                  &#8203;
                  <input type="checkbox" class="size-4 rounded border-gray-300" name="isCashOn"/>
                </div>

                <div>
                  <strong class="font-medium text-gray-900 sm:text-xs sm:font-sm"> Cashon Delivery </strong>
                </div>
              </label>
            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto">
                <label
                    for="destinationAddress"
                    name="destinationAddress"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="text"
                      id="destinationAddress"
                      name="destinationAddress"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Destination Address"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Destination Address
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
                      placeholder="Description Address"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Description Address
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
                      placeholder="Remitter Name"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                      Remitter Name
                    </span>
                  </label>
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
                      placeholder="Addressee Name"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                     Addressee Name
                    </span>
                  </label>
              </div>

            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto w-full">
                <label
                    for="price"
                    class="relative block rounded-md border border-gray-200 shadow-sm focus-within:border-blue-600 focus-within:ring-1 focus-within:ring-blue-600"
                  >
                    <input
                      type="number"
                      id="price"
                      name="price"
                      class="peer border-none bg-transparent placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0 p-2 w-full"
                      placeholder="Price"
                    />

                    <span
                      class="pointer-events-none absolute start-2.5 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs"
                    >
                     Price
                    </span>
                </label>
              </div>
              <div class="flex-auto w-full">
                <div>
                  <select
                    name="responsible"
                    id="responsible"
                    class="block border border-gray-200 shadow-sm w-full focus-within:border-blue-600 focus-within:ring-1 rounded-md bg-transparent border-gray-300 text-gray-700 text-sm sm:text-sm p-2 "
                  >
                    <option value="">Responsable</option>
                    <option value="JM">John Mayer</option>
                    <option value="SRV">Stevie Ray Vaughn</option>
                    <option value="JH">Jimi Hendrix</option>
                    <option value="BBK">B.B King</option>
                    <option value="AK">Albert King</option>
                    <option value="BG">Buddy Guy</option>
                    <option value="EC">Eric Clapton</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="mt-6">
              <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
                Envíar
              </button>
            </div>
            <c:if test="${not empty errorMessage}">
              <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center"
                style="color: red;">
                ${errorMessage}
              </div>
            </c:if>
          </form>
        </div>
      </div>
    </div>
  </main>
</body>