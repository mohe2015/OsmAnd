# Setup

Download https://download.bbbike.org/osm/bbbike/Darmstadt/Darmstadt.osm.pbf

Use https://wiki.openstreetmap.org/wiki/OsmAndMapCreator by downloading https://download.osmand.net/latest-night-build/OsmAndMapCreator-main.zip

```bash
cd tools/java-tools/OsmAndMapCreator
../gradlew buildDistribution
```

```
cd plugins/Osmand-HideAndSeek/
cp ../../../resources/poi/poi_types.xml .
cp ../../../resources/obf_creation/rendering_types.xml .
```

```xml
<poi_category name="jetlag" top="true">
    <poi_type name="park" tag="leisure" value="park" excluded_poi_additional_category="payment_type"/>
</poi_category>
```