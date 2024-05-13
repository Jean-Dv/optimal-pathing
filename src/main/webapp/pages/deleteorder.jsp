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
    <jsp:param name="title" value="Delete order" />
    <jsp:param name="hrefTitle" value="deleteorder.jsp" />
  </jsp:include>
  <%@ include file="../components/sidemenu.jsp" %>
  <main class="ml-60 max-h-screen p-8 mt-20 sm:overflow-auto sm:ml-16">
    <div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="grid grid-cols-1 gap-x-16 gap-y-8 lg:grid-cols-5">
        <form class="space-y-4">
          <div class="lg:col-span-2 lg:py-12">
            <h3 class="text-4xl text-black">Delete order</h3>
          </div>
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

            <h1 class="text-center"> Are you sure?</h1>
            <p class="mt-4 text-gray-500 text-center">
              You won't be able to revert this!
            </p>

            <div class="mt-6 sm:flex sm:gap-4 justify-center">
              <div class="mt-6">
                <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
               Yes, delete it!

              </button>
              </div>
              <div class="mt-6">
                <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
                Cancel
              </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </main>
  </body>
