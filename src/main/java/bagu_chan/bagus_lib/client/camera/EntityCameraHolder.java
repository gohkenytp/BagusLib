package bagu_chan.bagus_lib.client.camera;

import bagu_chan.bagus_lib.util.GlobalVec3;
import net.minecraft.world.entity.Entity;

public class EntityCameraHolder<T extends Entity> extends CameraHolder {
    private T entity;

    public EntityCameraHolder(int amount, int duration, GlobalVec3 pos, T entity) {
        super(amount, duration, pos);
        this.entity = entity;
    }

    public EntityCameraHolder(int distance, int duration, float amount, GlobalVec3 pos, T entity) {
        super(distance, duration, amount, pos);
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    @Override
    public GlobalVec3 getPos() {
        GlobalVec3 vec3 = super.getPos();
        if (entity != null) {
            return GlobalVec3.of(vec3.dimension(), entity.position());
        }
        return vec3;
    }
}
