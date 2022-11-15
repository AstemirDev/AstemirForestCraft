package org.astemir.api.client.animator;



import org.astemir.api.common.entity.AnimationFactory;


public interface IAnimated {

    <T extends IAnimated> AnimationFactory<T> getFactory();
}
