package bagu_chan.bagus_lib.client.camera;

import bagu_chan.bagus_lib.util.GlobalVec3;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.ViewportEvent;

public class CameraHolder {
    public int amount;
    public int duration;
    public int time;

    private final GlobalVec3 pos;

    public CameraHolder(int amount, int duration, GlobalVec3 pos) {
        this.amount = amount;
        this.duration = duration;
        this.pos = pos;
    }

    public int getAmount() {
        return amount;
    }

    public GlobalVec3 getPos() {
        return pos;
    }

    public int getDuration() {
        return duration;
    }

    private void preTick(ViewportEvent.ComputeCameraAngles event) {

        float dist = (float) Mth.clamp((float) this.amount * this.amount / this.getPos().pos().distanceToSqr(event.getCamera().getPosition()), 0F, 1F);
        float leftTick = ((float) this.getDuration() / (float) this.time);

        if (this.getPos().pos().distanceToSqr(event.getCamera().getPosition()) < this.amount * this.amount && event.getCamera().getEntity().level.dimension() == this.getPos().dimension()) {
            double ticks = event.getCamera().getEntity().tickCount + event.getPartialTick();
            float amount = leftTick * dist;

            event.setPitch(event.getPitch() + amount * Mth.cos((float) (ticks * 3F)) * 0.3F);
            event.setYaw(event.getYaw() + amount * Mth.cos((float) (ticks * 2.5F)) * 0.3F);
            event.setRoll(event.getRoll() + amount * Mth.cos((float) (ticks * 2F)) * 0.3F);
        }
    }

    public void tick(ViewportEvent.ComputeCameraAngles event) {
        ++this.time;
        preTick(event);
    }
}
