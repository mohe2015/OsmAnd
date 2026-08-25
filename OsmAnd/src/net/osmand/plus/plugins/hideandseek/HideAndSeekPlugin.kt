package net.osmand.plus.plugins.hideandseek

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import net.osmand.aidlapi.OsmAndCustomizationConstants
import net.osmand.data.LatLon
import net.osmand.data.PointDescription
import net.osmand.data.RotatedTileBox
import net.osmand.plus.OsmandApplication
import net.osmand.plus.R
import net.osmand.plus.activities.MapActivity
import net.osmand.plus.plugins.OsmandPlugin
import net.osmand.plus.plugins.audionotes.AudioVideoNotesPlugin
import net.osmand.plus.quickaction.QuickAction
import net.osmand.plus.quickaction.QuickActionIds
import net.osmand.plus.quickaction.QuickActionType
import net.osmand.plus.quickaction.actions.SelectMapLocationAction
import net.osmand.plus.views.layers.base.OsmandMapLayer
import net.osmand.plus.widgets.ctxmenu.ContextMenuAdapter
import net.osmand.plus.widgets.ctxmenu.callback.ItemClickListener
import net.osmand.plus.widgets.ctxmenu.callback.OnDataChangeUiAdapter
import net.osmand.plus.widgets.ctxmenu.data.ContextMenuItem

class HideAndSeekPlugin(app: OsmandApplication) : OsmandPlugin(app) {
    override fun getId(): String = "selfmade4u.hideandseek"

    override fun getName(): String = "Hide and Seek"

    override fun getDescription(linksEnabled: Boolean): CharSequence = "For playing Hide and Seek"

    override fun registerLayers(context: Context, mapActivity: MapActivity?) {
        app.getOsmandMap().mapView.addLayer(HideAndSeekLayer(context), 3.5f)
    }

    override fun getQuickActionTypes(): List<QuickActionType?> {
        val quickActionTypes = ArrayList<QuickActionType?>()
        quickActionTypes.add(HideAndSeekRadarAction.TYPE)
        return quickActionTypes
    }

    override fun registerMapContextMenuActions(
        mapActivity: MapActivity,
        latitude: Double,
        longitude: Double,
        adapter: ContextMenuAdapter,
        selectedObj: Any?,
        configureMenu: Boolean
    ) {
        adapter.addItem(
            ContextMenuItem(OsmAndCustomizationConstants.MAP_CONTEXT_MENU_AUDIO_NOTE)
                .setTitleId(R.string.recording_context_menu_arecord, app)
                .setIcon(R.drawable.ic_action_micro_dark)
                .setOrder(10)
                .setListener(ItemClickListener { uiAdapter: OnDataChangeUiAdapter?, view: View?, item: ContextMenuItem?, isChecked: Boolean ->

                    true
                })
        )
    }
}

// quick actions can be configured by the user in "Configure Screen"
class HideAndSeekRadarAction : SelectMapLocationAction {
    constructor() : super(TYPE)

    constructor(quickAction: QuickAction) : super(quickAction)

    override fun onLocationSelected(mapActivity: MapActivity, latLon: LatLon, params: Bundle?) {
        val lat = latLon.getLatitude()
        val lon = latLon.getLongitude()

        var pd = PointDescription(lat, lon)

        if (pd.isLocation() && pd.getName() == PointDescription.getAddressNotFoundStr(mapActivity)) {
            pd = PointDescription(PointDescription.POINT_TYPE_LOCATION, "")
        }

        mapActivity.getMapActions().addMapMarker(lat, lon, pd, null)
    }

    protected override fun getLocationIcon(mapActivity: MapActivity): Any? {
        val markersHelper = mapActivity.getApp().getMapMarkersHelper()
        val layer = mapActivity.getMapLayers().getMapMarkersLayer()
        val colorIndex = markersHelper.getNextMarkerColorIndex(-1)
        return layer.getMapMarkerShiftedBitmap(colorIndex)
    }

    protected override fun getQuickActionDescription(context: Context): CharSequence {
        return context.getString(R.string.quick_action_add_marker_descr)
    }

    companion object {
        val TYPE: QuickActionType = QuickActionType(
            QuickActionIds.MARKER_ACTION_ID,
            "hideandseek.radar", HideAndSeekRadarAction::class.java
        ).nameRes(R.string.hideandseek_radar).iconRes(R.drawable.ic_action_flag).nonEditable().category(QuickActionType.MY_PLACES).nameActionRes(R.string.shared_string_add)
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
        // LunarEclipseMapLayer for gpu assistance?
        val paint = Paint()

        canvas!!.drawCircle(0.0F, 0.0F, 300.0F, paint)

    }

    override fun drawInScreenPixels(): Boolean {
        return false
    }
}