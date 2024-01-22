package me.neptune.impl.module.combat;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.value.NumberValue;

public class Reach extends Module {
	public static Reach INSTANCE;

	private final NumberValue<Float> distance = new NumberValue<>(
			new String[]{"Distance", "distance"},
			"distance size",
			3.4f, 0.0f, 6.0f, 0.1f
	);

	public Reach() {
		super("Reach", new String[]{"Reach"}, "Allows you to interact and attack further", Category.COMBAT);
		this.offerValues(distance);

		INSTANCE = this;
	}

	public float getReach() {
		return distance.getValue().floatValue();
	}
	
	/*public void setReachLength(float reach) {
		this.distance.setValue(reach);
	}

	 */
}