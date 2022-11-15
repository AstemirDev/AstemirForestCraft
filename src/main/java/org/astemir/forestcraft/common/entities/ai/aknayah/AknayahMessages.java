package org.astemir.forestcraft.common.entities.ai.aknayah;

import org.astemir.api.loot.WeightedRandom;

public class AknayahMessages {


    public static WeightedRandom<String> RANDOM_WEAK_ATTACK_PHRASE = new WeightedRandom().
            add(30,"Do you think this is the best you can do?").
            add(30,"What a miserable show.").
            add(30,"What a disgraceful adversary.").
            add(30,"Pathetic...").
            add(30,"Boring...").
            add(30,"You're wasting my time.").
            add(30,"I didn't even feel your hit.").
            add(30,"Weak...").build();

    public static WeightedRandom<String> RANDOM_NORMAL_ATTACK_PHRASE = new WeightedRandom().
            add(30,"Your attempts are so lovely.").
            add(30,"Come on, come on, try harder.").
            add(30,"Try harder.").
            add(30,"Try again.").
            add(30,"Show me all you got.").
            add(30,"Your attacks is nothing for me.").build();

    public static WeightedRandom<String> RANDOM_STRONG_ATTACK_PHRASE = new WeightedRandom().
            add(30,"Haha, that's almost painful.").
            add(30,"Yes, show me all what you got.").
            add(30,"This is a true battle!").
            add(30,"Yes give me more fun.").
            add(30,"So powerful!").
            add(30,"I even got hurt. Awesome!").
            add(30,"Ouch!").build();

    public static WeightedRandom<String> RANDOM_IMPOSSIBLE_ATTACK_PHRASE = new WeightedRandom().
            add(30,"Wha..").
            add(30,"It was really painful.").
            add(30,"How did you?").
            add(30,"Ugh...").build();


    public static WeightedRandom<String> ATTACK = new WeightedRandom().
            add(30,"Get it!").
            add(30,"Punch.").
            add(30,"Accept your death.").
            add(30,"Don't resist.").
            add(30,"Don't be afraid, it won't hurt. Just kidding!").
            add(30,"I've met opponents more resistant than you.").
            add(30,"I'm returning the blow!").
            add(30,"I hope you get over it. It would be boring to end like this.").
            add(30,"Let's see if you can handle it.").
            add(30,"Get a slap!").
            add(30,"Oh, let me teach you a lesson.").build();

    public static WeightedRandom<String> ATTACK_INSECT = new WeightedRandom().
            add(30,"It's disgusting for me to hit you.").
            add(30,"Nasty thing!").
            add(30,"I will crush you like a fly.").
            add(30,"Ugly creature.").
            add(30,"Die insect!").
            add(30,"I'll rip your limbs off!").
            add(30,"So filthy.").build();

    public static WeightedRandom<String> ATTACK_UNDEAD = new WeightedRandom().
            add(30,"Stupid undead").
            add(30,"Don't fall apart from my blow.").
            add(30,"Unacceptable...").
            add(30,"You'll be a pile of flesh and bones.").
            add(30,"Get out of my sight.").build();

    public static WeightedRandom<String> REGENERATION = new WeightedRandom().
            add(30,"I'll heal a little.").
            add(30,"Regeneration! Ha-ha!").
            add(30,"Instant Health!").build();

    public static String[] DEATH_DIALOGUE = new String[]{
            "Impossible...",
            "Okay, I think you can really consider yourself the winner in this battle.",
            "Yes, yes, I admit defeat.",
            "Anyway, goodbye, I hope we don't meet again."};

}
