/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package sandbox.spacerunner.control;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.ents.component.Required;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.PositionComponent;
import sandbox.spacerunner.EntityType;
import sandbox.spacerunner.SpaceRunnerFactory;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
@Required(PositionComponent.class)
public class PlayerControl extends AbstractControl {

    private PositionComponent position;

    private double speed;

    @Override
    public void onAdded(Entity entity) {
        position = Entities.getPosition(entity);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        speed = tpf * 300;

        position.translateX(tpf * 60);
    }

    public void up() {
//        if (position.getY() - speed <= 0) {
//            position.setY(0);
//            return;
//        }

        position.translateY(-speed);
    }

    public void down() {
//        if (position.getY() + speed <= FXGL.getSettings().getHeight()) {
//            position.setY(FXGL.getSettings().getHeight());
//            return;
//        }

        position.translateY(speed);
    }

    public void shoot() {
        Entity bullet = FXGL.getInstance(SpaceRunnerFactory.class)
                .newBullet(position.getX() + 40, position.getY() + 20, EntityType.PLAYER);

        getEntity().getWorld().addEntity(bullet);
    }
}
