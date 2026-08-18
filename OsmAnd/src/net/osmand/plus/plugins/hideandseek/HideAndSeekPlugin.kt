package net.osmand.plus.plugins.hideandseek

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import net.osmand.data.RotatedTileBox
import net.osmand.plus.OsmandApplication
import net.osmand.plus.activities.MapActivity
import net.osmand.plus.plugins.OsmandPlugin
import net.osmand.plus.views.layers.MapVectorLayer
import net.osmand.plus.views.layers.base.OsmandMapLayer

class HideAndSeekPlugin(app: OsmandApplication) : OsmandPlugin(app) {
    override fun getId(): String = "selfmade4u.hideandseek"

    override fun getName(): String = "Hide and Seek"

    override fun getDescription(linksEnabled: Boolean): CharSequence = "For playing Hide and Seek"

    override fun registerLayers(context: Context, mapActivity: MapActivity?) {
        app.getOsmandMap().mapView.addLayer(HideAndSeekLayer(context), 3.5f)
    }
}

class HideAndSeekLayer(ctx: Context) : OsmandMapLayer(ctx) {
    override fun onDraw(
        canvas: Canvas?,
        tileBox: RotatedTileBox?,
        settings: DrawSettings?
    ) {
        // theoretically mapvector?
        // radius ruler control layer?
        val paint = Paint()

        canvas!!.drawCircle(0.0F, 0.0F, 300.0F, paint)

    }

    override fun drawInScreenPixels(): Boolean {
        return false
    }
}