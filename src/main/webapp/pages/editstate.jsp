<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ page import="co.edu.uptc.model.Order" %>
<%@ page import="java.util.List"%>
<%@ page import="co.edu.uptc.model.Responsible"%>
<% Order order = (Order) request.getSession().getAttribute("order"); %>
<html lang="en">
<head>
    <title>Inicio</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/import-js.jsp" %>
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
        integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
        crossorigin=""></script>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
     integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
     crossorigin=""/>
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
          <h3 class="text-4xl text-black">Orden: <%= order.getId() %></h3>
        </div>
        <div class="rounded-lg bg-white p-8 shadow-lg lg:col-span-3 lg:p-12">
          <form action="/project-programation/order" method="post" class="space-y-4">
            <input type="hidden" id="_method" name="_method" value="patch" />
            <input type="hidden" id="id" name="id" value="<%= order.getId() %>" />
            <div>
              <label
                for="isCashOn"
                class="flex items-start gap-4 rounded-lg border border-gray-200 p-4 transition bg-gray-200  has-[:checked]:bg-blue-50 cursor-not-allowed"
              >
                <div class="flex items-center">
                  &#8203;
                  <input
                    type="checkbox"
                    class="size-4 rounded border-gray-300" 
                    id="cashonDelivery" <%=order.isCashonDelivery() ? "checked" : "" %>
                    name="cashonDelivery"
                    disabled
                    />
                    
                </div>

                <div>
                  <strong  class="font-medium text-gray-900 sm:text-xs sm:font-sm"> Contra entrega </strong>
                </div>
              </label>
            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto">
                <label
                    for="destinationAddress"
                    class="block text-sm font-medium text-gray-700 mb-1"
                >
                   Dirección de destino
                </label>
                 <input
                      type="text"
                      id="destinationAddress"
                      name="destinationAddress"
                      class="border-none bg-gray-200 text-gray-700 p-2 w-full cursor-not-allowed"
                      placeholder="Nombre remitente"
                      value="${order.destinationAddress}"
                      readonly
                    />
              </div>
              <div class="flex-auto">
                <label
                    for="descriptionAddress"
                    class="block text-sm font-medium text-gray-700 mb-1"              
                >
                      Descripción de la dirección
                  </label>
                  <input
                        type="text"
                        id="descriptionAddress"
                        name="descriptionAddress"
                        class="border-none bg-gray-200 text-gray-700 p-2 w-full cursor-not-allowed"
                        placeholder="Descripción de dirección"
                        value="${order.description}"
                        readonly
                      />
              </div>
            </div>
            <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
              <div class="flex-auto">
                <label
                    for="remitterName"
                    class="block text-sm font-medium text-gray-700 mb-1"              
                  >
                      Nombre del remitente
                </label>
                <input
                      type="text"
                      id="remitterName"
                      name="remitterName"
                      class="border-none bg-gray-200 text-gray-700 p-2 w-full cursor-not-allowed"
                      placeholder="Dirección destino"
                      value="${order.remitterName}"
                      readonly
                    />
              </div>
              <div class="flex-auto">
                <label
                    for="addresseeName"
                    class="block text-sm font-medium text-gray-700 mb-1"              
                  >
                      Nombre del destinatario
                </label>
                <input
                      type="text"
                      id="addresseeName"
                      name="addresseeName"
                      class="border-none bg-gray-200 text-gray-700 p-2 w-full cursor-not-allowed"
                      placeholder="Nombre destinatario"
                      value="${order.addresseeName}"
                      readonly
                    />
              </div>

            </div>
                 <div class="flex flex-row gap-x-16 sm:flex-col sm:gap-y-4">
                    <div class="flex-auto w-full">
                      <label 
                         for="price"
                         class="block text-sm font-medium text-gray-700 mb-1"              
                      >
                          Precio
                      </label>
                      <input type="number"
                              id="price"
                              name="price"
                              class="border-none bg-gray-200 text-gray-700 p-2 w-full cursor-not-allowed"
                              placeholder="Precio"
                              value="${order.shippingValue}"
                              readonly
                        />
                    </div>

                    <div class="flex-auto w-full sm:ml-4">
                      <div class="flex gap-4">
                        <div class="w-full sm:w-1/2">
                          <label 
                            for="responsible"
                            class="block text-sm font-medium text-gray-700 mb-1"
                          >
                            Responsable
                          </label>
                          <select name="responsible"
                                    id="responsible"
                                    disabled
                                    class="border bg-gray-300 text-gray-700 p-2 w-full cursor-not-allowed rounded-md"
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
                          <label 
                            for="state"
                            class="block text-sm font-medium text-gray-700 mb-1"
                          >
                            Estado de orden
                          </label>
                          <select 
                            name="state"
                            id="state"
                            class="border border-gray-200 shadow-sm w-full focus-within:border-blue-600 focus-within:ring-1 rounded-md bg-transparent border-gray-300 p-2"
                          >
                            <option value="">Por favor seleccione</option>
                            <option value="Delivered" <%= order.getStatus().getStatus().equals("Delivered") ? "selected" : "" %>>Entregado</option>
                            <option value="Delay" <%= order.getStatus().getStatus().equals("Delay") ? "selected" : "" %>>Demorado</option>
                            <option value="Devolution" <%= order.getStatus().getStatus().equals("Devolution") ? "selected" : "" %>>Devolución</option>
                            <option value="On way" <%= order.getStatus().getStatus().equals("On way") ? "selected" : "" %>>En camino</option>
                            <option value="Warehouse exit" <%= order.getStatus().getStatus().equals("Warehouse exit") ? "selected" : "" %>>Salida del almacén</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>     

              <button
                type="submit"
                class="inline-block w-full rounded-lg bg-black px-5 py-3 font-medium text-white sm:w-auto"
              >
                Editar estado
              </button>
                <c:if test="${not empty all_fields_required}">
                  <div class="lg:col-span-2 lg:py-12 lg:text-center lg:pl-8 flex items-center justify-center"
                      style="color: red;">
                      ${all_fields_required}
                  </div>
                </c:if>
    
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="mx-auto max-w-screen-xl px-4 pt-8 pb-16 sm:px-6 lg:px-8">
      <div class="mb-10">
        <h3 class="text-4xl text-black">Rutas de la orden</h3>
      </div>
      <div id="map" class="h-96"></div>
    </div>
  </main>
</body>

<script>
  var map = L.map('map').setView([5.716, -72.9279328], 14);

  function addGeoJsonNodeToMap(node) {
    var geojsonMarkerOptions = {
      radius: 4,
      fillColor: "#ff7800",
      color: "#000",
      weight: 1,
      opacity: 1,
      fillOpacity: 0.8
    };
    let geoJSON = {
      "type": "Feature",
      "properties": node.properties,
      "geometry": {
        "type": "Point",
        "coordinates": node.geometry.coordinate.values
      }
    }
    L.geoJson(geoJSON, {
      pointToLayer: function (feature, latlng) {
        return L.circleMarker(latlng, geojsonMarkerOptions);
      }
    }).addTo(map)
  }

  function addGeoJsonPolygonToMap(polygon) {
    let coordinates = [];
    for (let coordinate of polygon.geometry.coordinates) {
      coordinates.push([coordinate.values[0], coordinate.values[1]]);
    }
    let geoJSON = {
      "type": "Feature",
      "properties": polygon.properties,
      "geometry": {
        "type": "LineString",
        "coordinates": coordinates
      }
    }
    return {
      geoJSON,
      distance: polygon.properties.length / 1000,
      maxSpeed: polygon.properties.maxspeed
    };
  }

  function addGeojsonPolygonsToMap(polygons, color) {
    let length = 0;
    let speeds = 0;
    const geoJson = [];
    const style = {
      color,
      "weight": 2,
      "opacity": 1
    }
    for (let polygon of polygons) {
      const polygonResponse = addGeoJsonPolygonToMap(polygon);
      geoJson.push(polygonResponse.geoJSON);
      length += polygonResponse.distance;
      speeds += polygonResponse.maxSpeed;
    }
    const date = new Date(0, 0);
    date.setSeconds(+(length / (speeds / polygons.length)) * 60 * 60); 
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    L.geoJson(geoJson).addTo(map).setStyle(style).bindPopup("Distancia: " + length.toFixed(2) + " km\n Velocidad máxima: " + (speeds / polygons.length).toFixed(2) + " km/h \n Tiempo estimado: " + date.toTimeString().slice(0, 8));
  }

  async function fetchGeojson() {

    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    const response = await fetch('/project-programation/routes?id=' + id);
    fetch('/project-programation/routes?id=' + id)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        addGeoJsonNodeToMap(data.start);
        addGeoJsonNodeToMap(data.finish);
        addGeojsonPolygonsToMap(data.data[1].edges, data.data[1].color,);
        addGeojsonPolygonsToMap(data.data[2].edges, data.data[2].color);
        addGeojsonPolygonsToMap(data.data[0].edges, data.data[0].color);
      });
  }
  fetchGeojson();
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);
</script>