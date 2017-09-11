package it.unibo.radargui;

import it.unibo.is.interfaces.IOutputView;
import it.unibo.qactors.QActorContext;
import it.unibo.radar.gui.CtrlDashSwingImpl;
import it.unibo.radar.gui.GaugeDisplaySwingImpl;
import it.unibo.radar.gui.Position2D;
import it.unibo.radar.gui.RadarView;
import it.unibo.radar.gui.RadarViewSwingImpl;
import it.unibo.radar.gui.RadarWithView;
import it.unibo.radar.interfaces.IPosition2D;
import it.unibo.radar.interfaces.IRadar;
import it.unibo.radar.interfaces.IRadarView;
import it.unibo.radar.interfaces.IRadarViewSwingImpl;
import it.unibo.system.SituatedPlainObject;

public class RadarControl extends SituatedPlainObject {
	protected IRadar radar;
	protected IRadarView View;
	protected IRadarViewSwingImpl ViewImpl;
	protected GaugeDisplaySwingImpl gaugeDisplayImpl;
	protected CtrlDashSwingImpl ctrlDashImpl;

	public RadarControl(QActorContext ctx, IOutputView outView) throws Exception {
		super(outView);
		initGauges();
		initDashBoard();
	}

	protected void initGauges() {
		this.ViewImpl = RadarViewSwingImpl.create("RadarView");
		this.View = RadarView.create(this.ViewImpl);
		this.radar = RadarWithView.create(this.View);
	}

	public void initDashBoard() {
		this.gaugeDisplayImpl = GaugeDisplaySwingImpl.create("RadarDisplay", this.ViewImpl);
		this.ctrlDashImpl = CtrlDashSwingImpl.create("Dashboard", this.gaugeDisplayImpl);
		this.ctrlDashImpl.start(SonarRadarKb.winWith, SonarRadarKb.winWith);
	}

	public void update(String dist, String theta) {
		try {
			double distance = Double.parseDouble(dist);
			double arg = Double.parseDouble(theta);
			IPosition2D p0 = Position2D.createPolar(distance, arg);
			if (this.radar == null){
				return;
			}
			this.radar.update(p0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
