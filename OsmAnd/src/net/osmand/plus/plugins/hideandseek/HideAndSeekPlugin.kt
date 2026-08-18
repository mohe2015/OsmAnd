package net.osmand.plus.plugins.hideandseek

import android.content.Context
import net.osmand.plus.OsmandApplication
import net.osmand.plus.activities.MapActivity
import net.osmand.plus.plugins.OsmandPlugin
import net.osmand.plus.views.layers.MapVectorLayer

class HideAndSeekPlugin(app: OsmandApplication) : OsmandPlugin(app) {
    override fun getId(): String = "selfmade4u.hideandseek"

    override fun getName(): String = "Hide and Seek"

    override fun getDescription(linksEnabled: Boolean): CharSequence = "For playing Hide and Seek"

    override fun registerLayers(context: Context, mapActivity: MapActivity?) {
        app.getOsmandMap().mapView.addLayer(MapVectorLayer(context), 3.5f)

    }
}