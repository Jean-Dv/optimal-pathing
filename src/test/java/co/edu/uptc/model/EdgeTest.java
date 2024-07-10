package co.edu.uptc.model;

import static org.junit.Assert.assertEquals;

import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Position;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.Test;

/**
 * This class represents a node in the digraph.
 */
public class EdgeTest {

  private String geoJson = """
      {
        "type": "Feature",
        "id": 0.0,
        "properties": {
          "u": 316951892.0,
          "v": 5613812079.0,
          "key": 0.0,
          "osmid": [
            585952937.0,
            585952939.0,
            585952940.0,
            1067403003.0,
            587726557.0
          ],
          "highway": "unclassified",
          "oneway": false,
          "reversed": [
            false,
            true
          ],
          "length": 872.179,
          "maxspeed": 30.0,
          "weight": 29.072633333333336,
          "lanes": null,
          "ref": null,
          "name": null,
          "width": null,
          "bridge": null,
          "junction": null
        },
        "geometry": {
          "type": "LineString",
          "coordinates": [
            [
              -72.9289882,
              5.6652957
            ],
            [
              -72.9285979,
              5.6654118
            ],
            [
              -72.9285403,
              5.6654145
            ],
            [
              -72.9284853,
              5.6653971
            ],
            [
              -72.9284357,
              5.6653144
            ],
            [
              -72.928488,
              5.6648646
            ],
            [
              -72.9284424,
              5.6644896
            ],
            [
              -72.928441,
              5.6644175
            ],
            [
              -72.9284678,
              5.6643308
            ],
            [
              -72.928897,
              5.6637796
            ],
            [
              -72.9289372,
              5.6636368
            ],
            [
              -72.9289386,
              5.66351
            ],
            [
              -72.9289171,
              5.6633819
            ],
            [
              -72.9288742,
              5.6632592
            ],
            [
              -72.9287991,
              5.6632178
            ],
            [
              -72.9287119,
              5.6632325
            ],
            [
              -72.9286784,
              5.6633099
            ],
            [
              -72.9286556,
              5.6634246
            ],
            [
              -72.9285966,
              5.6635314
            ],
            [
              -72.9285134,
              5.6635728
            ],
            [
              -72.9284383,
              5.6635634
            ],
            [
              -72.9283673,
              5.6635047
            ],
            [
              -72.9282975,
              5.663474
            ],
            [
              -72.9280736,
              5.6633939
            ],
            [
              -72.9280427,
              5.6633486
            ],
            [
              -72.9280601,
              5.6632592
            ],
            [
              -72.9281406,
              5.663127
            ],
            [
              -72.928145,
              5.6630511
            ],
            [
              -72.9281299,
              5.662984
            ],
            [
              -72.9280685,
              5.6628298
            ],
            [
              -72.9279993,
              5.6627231
            ],
            [
              -72.9278934,
              5.6626255
            ],
            [
              -72.9278105,
              5.6625631
            ],
            [
              -72.9277305,
              5.6624939
            ],
            [
              -72.9276957,
              5.6624762
            ],
            [
              -72.9276501,
              5.6624775
            ],
            [
              -72.9275378,
              5.6625414
            ],
            [
              -72.9274433,
              5.6626119
            ],
            [
              -72.9272796,
              5.6627734
            ],
            [
              -72.9272254,
              5.6628178
            ],
            [
              -72.9271581,
              5.6628534
            ],
            [
              -72.9271012,
              5.6628733
            ],
            [
              -72.9270294,
              5.6628845
            ],
            [
              -72.9267955,
              5.6629108
            ],
            [
              -72.926707,
              5.6629229
            ],
            [
              -72.9265943,
              5.6629442
            ],
            [
              -72.926467,
              5.6629962
            ],
            [
              -72.9262859,
              5.6630656
            ],
            [
              -72.926217,
              5.663089
            ],
            [
              -72.9260606,
              5.6631057
            ],
            [
              -72.9259586,
              5.6630283
            ],
            [
              -72.9258862,
              5.6629002
            ],
            [
              -72.925861,
              5.662811
            ],
            [
              -72.9258594,
              5.6627187
            ],
            [
              -72.9258711,
              5.6626119
            ],
            [
              -72.9258928,
              5.6624522
            ],
            [
              -72.9258903,
              5.6623413
            ],
            [
              -72.9258705,
              5.6622656
            ],
            [
              -72.9257965,
              5.6622131
            ]
          ]
        }
      }
            """;

  @Test
  public void testFromDocument() {
    Edge edge = Edge.fromDocument(Document.parse(this.geoJson));
    assertEquals("Feature", edge.getType().toString());

    assertEquals("[-72.9289882, 5.6652957]",
        edge.getGeometry().getCoordinates().get(0).getValues().toString());
  }

  @Test
  public void testToDocument() {

    PropertiesEdge propertiesEdge = new PropertiesEdge(316951892.0, 131231231.0, 0, null, null,
        false, null, 0, 0, 0, null, null, null, null, null);

    Position position1 = new Position(-72.9289882, 5.6652957);
    Position position2 = new Position(-72.9285979, 5.6654118);
    Position position3 = new Position(-72.9285403, 5.6654145);

    List<Position> positions = new ArrayList<>();
    positions.add(position3);
    positions.add(position2);
    positions.add(position1);
    LineString geometry = new LineString(positions);

    Edge edge = new Edge(12.0, propertiesEdge, geometry);

    assertEquals(12.0, edge.toDocument().get("id"));
  }

}
