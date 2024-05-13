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
    <jsp:param name="title" value="Home" />
    <jsp:param name="hrefTitle" value="orders.jsp" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>

  <main class="ml-60 max-h-screen overflow-auto p-8 mt-20 sm:ml-16">
    <div class="flex justify-end mb-2">
      <div class="inline-flex rounded-lg border border-gray-100 bg-gray-100 p-1 justify-between">
        <button
          type="button"
          class="inline-flex items-center gap-2 rounded-md px-4 py-2 text-sm text-gray-500 hover:text-gray-700 focus:relative"
        >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M6 8C6 7.44772 6.44772 7 7 7H17C17.5523 7 18 7.44772 18 8C18 8.55228 17.5523 9 17 9H7C6.44772 9 6 8.55228 6 8Z" fill="currentColor" /><path d="M8 12C8 11.4477 8.44772 11 9 11H15C15.5523 11 16 11.4477 16 12C16 12.5523 15.5523 13 15 13H9C8.44772 13 8 12.5523 8 12Z" fill="currentColor" /><path d="M11 15C10.4477 15 10 15.4477 10 16C10 16.5523 10.4477 17 11 17H13C13.5523 17 14 16.5523 14 16C14 15.4477 13.5523 15 13 15H11Z" fill="currentColor" /></svg>

          Ordenar
        </button>
        <button
        type="button"
        class="inline-flex items-center gap-2 rounded-md bg-white px-4 py-2 text-sm text-gray-500 hover:text-gray-700 focus:relative"
        onclick="window.location.href='addorder.jsp'"
      >
        <svg
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22C6.47715 22 2 17.5228 2 12ZM12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4Z"
            fill="currentColor"
          />
          <path
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M13 7C13 6.44772 12.5523 6 12 6C11.4477 6 11 6.44772 11 7V11H7C6.44772 11 6 11.4477 6 12C6 12.5523 6.44772 13 7 13H11V17C11 17.5523 11.4477 18 12 18C12.5523 18 13 17.5523 13 17V13H17C17.5523 13 18 12.5523 18 12C18 11.4477 17.5523 11 17 11H13V7Z"
            fill="currentColor"
          />
        </svg>

        Añadir
      </button>

      </div>
    </div>
    <div class="overflow-x-auto rounded-lg border border-gray-200">
      <table class="min-w-full divide-y-2 divide-gray-200 bg-white text-sm">
        <thead class="text-left">
          <tr>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Order</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Remitter Name</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Status</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Destination Address</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Addressee Name</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Cashon Delivery</th>
            <th class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">Actions</th>
          </tr>
        </thead>

        <tbody class="divide-y divide-gray-200">
          <tr>
            <td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">1</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Catherina Madine</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">good</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">5383 Ramsey Avenue</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Dugald Brennand</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">true</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                <button
                  class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                  title="Edit order"
                  onclick="window.location.href='editorder.jsp'"
                >
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
              </span>
            </td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                <button
                  class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                  title="Delete order"
                  onclick="window.location.href='deleteorder.jsp'"
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

          <tr>
            <td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">2</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Helen-elizabeth Boagey</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">good</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">3743 Vermont Plaza</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Laurie Housby</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">false</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
              <button
                class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                title="Edit order"
                onclick="window.location.href='editorder.jsp'"
              >
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
            </span>
            </td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                <button
                  class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                  title="Delete order"
                  onclick="window.location.href='deleteorder.jsp'"
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

          <tr>
            <td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">3</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Humphrey Cosgrave</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">good</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">1 Nevada Circle</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">Egbert Bedder</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">true</td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                <button
                  class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                  title="Edit order"
                  onclick="window.location.href='editorder.jsp'"
                >
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
              </span>
            </td>
            <td class="whitespace-nowrap px-4 py-2 text-gray-700">
              <span class="inline-flex overflow-hidden rounded-md border bg-white shadow-sm">
                <button
                  class="inline-block border-e p-3 text-gray-700 hover:bg-gray-50 focus:relative"
                  title="Delete order"
                  onclick="window.location.href='deleteorder.jsp'"
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
        </tbody>
      </table>
    </div>
  </main>
</body>
