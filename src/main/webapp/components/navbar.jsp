<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<% String section = request.getParameter("section"); %>
<% String title = request.getParameter("title"); %>
<% String hrefSection = request.getParameter("hrefSection"); %>
<% String hrefTitle = request.getParameter("hrefTitle"); %>
<nav aria-label="Breadcrumb" class="flex fixed right-0 top-0 left-60 py-3 px-4 h-16 justify-center sm:left-16">
    <ol class="flex overflow-hidden rounded-lg border border-gray-200 text-gray-600">
      <li class="flex items-center">
        <a href=<%= hrefSection %> class="flex h-10 items-center gap-1.5 bg-gray-100 px-4 transition hover:text-gray-900">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
              d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
          </svg>

          <span class="ms-1.5 text-xs font-medium"> <%= section %> </span>
        </a>
      </li>

      <li class="relative flex items-center">
        <span
        class="absolute inset-y-0 -start-px h-10 w-4 bg-gray-100 [clip-path:_polygon(0_0,_0%_100%,_100%_50%)] rtl:rotate-180"
      >
      </span>

        <a href="<%= hrefTitle %>"
          class="flex h-10 items-center bg-white pe-4 ps-8 text-xs font-medium transition hover:text-gray-900">
          <%= title %>
        </a>
      </li>
    </ol>
  </nav>