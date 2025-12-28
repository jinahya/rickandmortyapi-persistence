package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants for the {@value Character_#TYPE} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Character_TypeColumnValues
 */
public enum Character_Type implements _StringColumnEnum<Character_Type> {

    /**
     * A constant for the {@value Character_TypeColumnValues#ALLIGATOR_PERSON} column value.
     */
    ALLIGATOR_PERSON(Character_TypeColumnValues.ALLIGATOR_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#ALPHABETRIAN} column value.
     */
    ALPHABETRIAN(Character_TypeColumnValues.ALPHABETRIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#AMOEBA_PERSON} column value.
     */
    AMOEBA_PERSON(Character_TypeColumnValues.AMOEBA_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#ANIMAL} column value.
     */
    ANIMAL(Character_TypeColumnValues.ANIMAL),

    /**
     * A constant for the {@value Character_TypeColumnValues#ANIME} column value.
     */
    ANIME(Character_TypeColumnValues.ANIME),

    /**
     * A constant for the {@value Character_TypeColumnValues#ARTIFICIAL_INTELLIGENCE} column value.
     */
    ARTIFICIAL_INTELLIGENCE(Character_TypeColumnValues.ARTIFICIAL_INTELLIGENCE),

    /**
     * A constant for the {@value Character_TypeColumnValues#BEPISIAN} column value.
     */
    BEPISIAN(Character_TypeColumnValues.BEPISIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#BIRD_PERSON} column value.
     */
    BIRD_PERSON(Character_TypeColumnValues.BIRD_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#BIRD_PERSON_HUMAN_MIX} column value.
     */
    BIRD_PERSON_HUMAN_MIX(Character_TypeColumnValues.BIRD_PERSON_HUMAN_MIX),

    /**
     * A constant for the {@value Character_TypeColumnValues#BLUE_APE_ALIEN} column value.
     */
    BLUE_APE_ALIEN(Character_TypeColumnValues.BLUE_APE_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#BOOBLE_BUYER_REPTILIAN} column value.
     */
    BOOBLE_BUYER_REPTILIAN(Character_TypeColumnValues.BOOBLE_BUYER_REPTILIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#BOOBLOOSIAN} column value.
     */
    BOOBLOOSIAN(Character_TypeColumnValues.BOOBLOOSIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#BREAD} column value.
     */
    BREAD(Character_TypeColumnValues.BREAD),

    /**
     * A constant for the {@value Character_TypeColumnValues#CAT} column value.
     */
    CAT(Character_TypeColumnValues.CAT),

    /**
     * A constant for the {@value Character_TypeColumnValues#CAT_CONTROLLED_DEAD_LADY} column value.
     */
    CAT_CONTROLLED_DEAD_LADY(Character_TypeColumnValues.CAT_CONTROLLED_DEAD_LADY),

    /**
     * A constant for the {@value Character_TypeColumnValues#CAT_PERSON} column value.
     */
    CAT_PERSON(Character_TypeColumnValues.CAT_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#CATERPILLAR} column value.
     */
    CATERPILLAR(Character_TypeColumnValues.CATERPILLAR),

    /**
     * A constant for the {@value Character_TypeColumnValues#CENTAUR} column value.
     */
    CENTAUR(Character_TypeColumnValues.CENTAUR),

    /**
     * A constant for the {@value Character_TypeColumnValues#CHAIR} column value.
     */
    CHAIR(Character_TypeColumnValues.CHAIR),

    /**
     * A constant for the {@value Character_TypeColumnValues#CHANGEFORMER} column value.
     */
    CHANGEFORMER(Character_TypeColumnValues.CHANGEFORMER),

    /**
     * A constant for the {@value Character_TypeColumnValues#CHUD} column value.
     */
    CHUD(Character_TypeColumnValues.CHUD),

    /**
     * A constant for the {@value Character_TypeColumnValues#CHUD_HUMAN_MIX} column value.
     */
    CHUD_HUMAN_MIX(Character_TypeColumnValues.CHUD_HUMAN_MIX),

    /**
     * A constant for the {@value Character_TypeColumnValues#CLAY_PERSON} column value.
     */
    CLAY_PERSON(Character_TypeColumnValues.CLAY_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#CLONE} column value.
     */
    CLONE(Character_TypeColumnValues.CLONE),

    /**
     * A constant for the {@value Character_TypeColumnValues#CONE_NIPPLED_ALIEN} column value.
     */
    CONE_NIPPLED_ALIEN(Character_TypeColumnValues.CONE_NIPPLED_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#CONJOINED_TWIN} column value.
     */
    CONJOINED_TWIN(Character_TypeColumnValues.CONJOINED_TWIN),

    /**
     * A constant for the {@value Character_TypeColumnValues#COOKIE} column value.
     */
    COOKIE(Character_TypeColumnValues.COOKIE),

    /**
     * A constant for the {@value Character_TypeColumnValues#CORN_PERSON} column value.
     */
    CORN_PERSON(Character_TypeColumnValues.CORN_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#CROMULON} column value.
     */
    CROMULON(Character_TypeColumnValues.CROMULON),

    /**
     * A constant for the {@value Character_TypeColumnValues#CRONENBERG} column value.
     */
    CRONENBERG(Character_TypeColumnValues.CRONENBERG),

    /**
     * A constant for the {@value Character_TypeColumnValues#CROW} column value.
     */
    CROW(Character_TypeColumnValues.CROW),

    /**
     * A constant for the {@value Character_TypeColumnValues#CROW_HORSE} column value.
     */
    CROW_HORSE(Character_TypeColumnValues.CROW_HORSE),

    /**
     * A constant for the {@value Character_TypeColumnValues#CYBORG} column value.
     */
    CYBORG(Character_TypeColumnValues.CYBORG),

    /**
     * A constant for the {@value Character_TypeColumnValues#DECOY} column value.
     */
    DECOY(Character_TypeColumnValues.DECOY),

    /**
     * A constant for the {@value Character_TypeColumnValues#DEMON} column value.
     */
    DEMON(Character_TypeColumnValues.DEMON),

    /**
     * A constant for the {@value Character_TypeColumnValues#DOG} column value.
     */
    DOG(Character_TypeColumnValues.DOG),

    /**
     * A constant for the {@value Character_TypeColumnValues#DOOPIDOO} column value.
     */
    DOOPIDOO(Character_TypeColumnValues.DOOPIDOO),

    /**
     * A constant for the {@value Character_TypeColumnValues#DRAGON} column value.
     */
    DRAGON(Character_TypeColumnValues.DRAGON),

    /**
     * A constant for the {@value Character_TypeColumnValues#DRUMBLOXIAN} column value.
     */
    DRUMBLOXIAN(Character_TypeColumnValues.DRUMBLOXIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#DUMMY} column value.
     */
    DUMMY(Character_TypeColumnValues.DUMMY),

    /**
     * A constant for the {@value Character_TypeColumnValues#EAT_SHITER_PERSON} column value.
     */
    EAT_SHITER_PERSON(Character_TypeColumnValues.EAT_SHITER_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#EEL} column value.
     */
    EEL(Character_TypeColumnValues.EEL),

    /**
     * A constant for the {@value Character_TypeColumnValues#ELEPHANT_PERSON} column value.
     */
    ELEPHANT_PERSON(Character_TypeColumnValues.ELEPHANT_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#FERKUSIAN} column value.
     */
    FERKUSIAN(Character_TypeColumnValues.FERKUSIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#FERRET_ROBOT} column value.
     */
    FERRET_ROBOT(Character_TypeColumnValues.FERRET_ROBOT),

    /**
     * A constant for the {@value Character_TypeColumnValues#FISH_PERSON} column value.
     */
    FISH_PERSON(Character_TypeColumnValues.FISH_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#FLANSIAN} column value.
     */
    FLANSIAN(Character_TypeColumnValues.FLANSIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#FLOOP_FLOOPIAN} column value.
     */
    FLOOP_FLOOPIAN(Character_TypeColumnValues.FLOOP_FLOOPIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#FLY} column value.
     */
    FLY(Character_TypeColumnValues.FLY),

    /**
     * A constant for the {@value Character_TypeColumnValues#GAME} column value.
     */
    GAME(Character_TypeColumnValues.GAME),

    /**
     * A constant for the {@value Character_TypeColumnValues#GARBLOVIAN} column value.
     */
    GARBLOVIAN(Character_TypeColumnValues.GARBLOVIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#GAZORPIAN} column value.
     */
    GAZORPIAN(Character_TypeColumnValues.GAZORPIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#GAZORPIAN_REPRODUCTION_ROBOT} column value.
     */
    GAZORPIAN_REPRODUCTION_ROBOT(Character_TypeColumnValues.GAZORPIAN_REPRODUCTION_ROBOT),

    /**
     * A constant for the {@value Character_TypeColumnValues#GEAR_PERSON} column value.
     */
    GEAR_PERSON(Character_TypeColumnValues.GEAR_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#GENETIC_EXPERIMENT} column value.
     */
    GENETIC_EXPERIMENT(Character_TypeColumnValues.GENETIC_EXPERIMENT),

    /**
     * A constant for the {@value Character_TypeColumnValues#GIANT} column value.
     */
    GIANT(Character_TypeColumnValues.GIANT),

    /**
     * A constant for the {@value Character_TypeColumnValues#GIANT_CAT_MONSTER} column value.
     */
    GIANT_CAT_MONSTER(Character_TypeColumnValues.GIANT_CAT_MONSTER),

    /**
     * A constant for the {@value Character_TypeColumnValues#GIANT_INCEST_BABY} column value.
     */
    GIANT_INCEST_BABY(Character_TypeColumnValues.GIANT_INCEST_BABY),

    /**
     * A constant for the {@value Character_TypeColumnValues#GLORZO} column value.
     */
    GLORZO(Character_TypeColumnValues.GLORZO),

    /**
     * A constant for the {@value Character_TypeColumnValues#GOD} column value.
     */
    GOD(Character_TypeColumnValues.GOD),

    /**
     * A constant for the {@value Character_TypeColumnValues#GODDESS} column value.
     */
    GODDESS(Character_TypeColumnValues.GODDESS),

    /**
     * A constant for the {@value Character_TypeColumnValues#GRAMUFLAKIAN} column value.
     */
    GRAMUFLAKIAN(Character_TypeColumnValues.GRAMUFLAKIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#GRANDMA} column value.
     */
    GRANDMA(Character_TypeColumnValues.GRANDMA),

    /**
     * A constant for the {@value Character_TypeColumnValues#GREEBYBOBE} column value.
     */
    GREEBYBOBE(Character_TypeColumnValues.GREEBYBOBE),

    /**
     * A constant for the {@value Character_TypeColumnValues#GROMFLOMITE} column value.
     */
    GROMFLOMITE(Character_TypeColumnValues.GROMFLOMITE),

    /**
     * A constant for the {@value Character_TypeColumnValues#GUINEA_PIG_FOR_THE_POLIO_VACCINE} column value.
     */
    GUINEA_PIG_FOR_THE_POLIO_VACCINE(Character_TypeColumnValues.GUINEA_PIG_FOR_THE_POLIO_VACCINE),

    /**
     * A constant for the {@value Character_TypeColumnValues#HAIRY_ALIEN} column value.
     */
    HAIRY_ALIEN(Character_TypeColumnValues.HAIRY_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#HALF_SOULLESS_PUPPET} column value.
     */
    HALF_SOULLESS_PUPPET(Character_TypeColumnValues.HALF_SOULLESS_PUPPET),

    /**
     * A constant for the {@value Character_TypeColumnValues#HAMMERHEAD_PERSON} column value.
     */
    HAMMERHEAD_PERSON(Character_TypeColumnValues.HAMMERHEAD_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#HIVEMIND} column value.
     */
    HIVEMIND(Character_TypeColumnValues.HIVEMIND),

    /**
     * A constant for the {@value Character_TypeColumnValues#HOLE} column value.
     */
    HOLE(Character_TypeColumnValues.HOLE),

    /**
     * A constant for the {@value Character_TypeColumnValues#HOLOGRAM} column value.
     */
    HOLOGRAM(Character_TypeColumnValues.HOLOGRAM),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_GAZORPIAN} column value.
     */
    HUMAN_GAZORPIAN(Character_TypeColumnValues.HUMAN_GAZORPIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_SNAKE_HYBRID} column value.
     */
    HUMAN_SNAKE_HYBRID(Character_TypeColumnValues.HUMAN_SNAKE_HYBRID),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_A_FLOWER_IN_HIS_HEAD} column value.
     */
    HUMAN_WITH_A_FLOWER_IN_HIS_HEAD(Character_TypeColumnValues.HUMAN_WITH_A_FLOWER_IN_HIS_HEAD),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_ANTENNAE} column value.
     */
    HUMAN_WITH_ANTENNAE(Character_TypeColumnValues.HUMAN_WITH_ANTENNAE),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_ANTS_IN_HIS_EYES} column value.
     */
    HUMAN_WITH_ANTS_IN_HIS_EYES(Character_TypeColumnValues.HUMAN_WITH_ANTS_IN_HIS_EYES),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_BABY_LEGS} column value.
     */
    HUMAN_WITH_BABY_LEGS(Character_TypeColumnValues.HUMAN_WITH_BABY_LEGS),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_GIANT_HEAD} column value.
     */
    HUMAN_WITH_GIANT_HEAD(Character_TypeColumnValues.HUMAN_WITH_GIANT_HEAD),

    /**
     * A constant for the {@value Character_TypeColumnValues#HUMAN_WITH_TUSKS} column value.
     */
    HUMAN_WITH_TUSKS(Character_TypeColumnValues.HUMAN_WITH_TUSKS),

    /**
     * A constant for the {@value Character_TypeColumnValues#INTERDIMENSIONAL_GASEOUS_BEING} column value.
     */
    INTERDIMENSIONAL_GASEOUS_BEING(Character_TypeColumnValues.INTERDIMENSIONAL_GASEOUS_BEING),

    /**
     * A constant for the {@value Character_TypeColumnValues#JELLYBEAN} column value.
     */
    JELLYBEAN(Character_TypeColumnValues.JELLYBEAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#KORBLOCK} column value.
     */
    KORBLOCK(Character_TypeColumnValues.KORBLOCK),

    /**
     * A constant for the {@value Character_TypeColumnValues#KROOTABULAN} column value.
     */
    KROOTABULAN(Character_TypeColumnValues.KROOTABULAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#LARVA_ALIEN} column value.
     */
    LARVA_ALIEN(Character_TypeColumnValues.LARVA_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#LEPRECHAUN} column value.
     */
    LEPRECHAUN(Character_TypeColumnValues.LEPRECHAUN),

    /**
     * A constant for the {@value Character_TypeColumnValues#LIGHT_BULB_ALIEN} column value.
     */
    LIGHT_BULB_ALIEN(Character_TypeColumnValues.LIGHT_BULB_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#LITTLE_HUMAN} column value.
     */
    LITTLE_HUMAN(Character_TypeColumnValues.LITTLE_HUMAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#LIZARD} column value.
     */
    LIZARD(Character_TypeColumnValues.LIZARD),

    /**
     * A constant for the {@value Character_TypeColumnValues#LIZARD_PERSON} column value.
     */
    LIZARD_PERSON(Character_TypeColumnValues.LIZARD_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#LOBSTER_ALIEN} column value.
     */
    LOBSTER_ALIEN(Character_TypeColumnValues.LOBSTER_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#MANNIE} column value.
     */
    MANNIE(Character_TypeColumnValues.MANNIE),

    /**
     * A constant for the {@value Character_TypeColumnValues#MASCOT} column value.
     */
    MASCOT(Character_TypeColumnValues.MASCOT),

    /**
     * A constant for the {@value Character_TypeColumnValues#MEESEEKS} column value.
     */
    MEESEEKS(Character_TypeColumnValues.MEESEEKS),

    /**
     * A constant for the {@value Character_TypeColumnValues#MEGA_GARGANTUAN} column value.
     */
    MEGA_GARGANTUAN(Character_TypeColumnValues.MEGA_GARGANTUAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#MEMORY} column value.
     */
    MEMORY(Character_TypeColumnValues.MEMORY),

    /**
     * A constant for the {@value Character_TypeColumnValues#MEXICAN} column value.
     */
    MEXICAN(Character_TypeColumnValues.MEXICAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#MICROVERSE_INHABITANT} column value.
     */
    MICROVERSE_INHABITANT(Character_TypeColumnValues.MICROVERSE_INHABITANT),

    /**
     * A constant for the {@value Character_TypeColumnValues#MINIVERSE_INHABITANT} column value.
     */
    MINIVERSE_INHABITANT(Character_TypeColumnValues.MINIVERSE_INHABITANT),

    /**
     * A constant for the {@value Character_TypeColumnValues#MONOGATRON} column value.
     */
    MONOGATRON(Character_TypeColumnValues.MONOGATRON),

    /**
     * A constant for the {@value Character_TypeColumnValues#MONSTER} column value.
     */
    MONSTER(Character_TypeColumnValues.MONSTER),

    /**
     * A constant for the {@value Character_TypeColumnValues#MORGLUTZIAN} column value.
     */
    MORGLUTZIAN(Character_TypeColumnValues.MORGLUTZIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#MORTYS_TOXIC_SIDE} column value.
     */
    MORTYS_TOXIC_SIDE(Character_TypeColumnValues.MORTYS_TOXIC_SIDE),

    /**
     * A constant for the {@value Character_TypeColumnValues#MYTHOLOG} column value.
     */
    MYTHOLOG(Character_TypeColumnValues.MYTHOLOG),

    /**
     * A constant for the {@value Character_TypeColumnValues#NANO_ALIEN} column value.
     */
    NANO_ALIEN(Character_TypeColumnValues.NANO_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#NARNIAN} column value.
     */
    NARNIAN(Character_TypeColumnValues.NARNIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#NECROPHILIAC} column value.
     */
    NECROPHILIAC(Character_TypeColumnValues.NECROPHILIAC),

    /**
     * A constant for the {@value Character_TypeColumnValues#NORMAL_SIZE_BUG} column value.
     */
    NORMAL_SIZE_BUG(Character_TypeColumnValues.NORMAL_SIZE_BUG),

    /**
     * A constant for the {@value Character_TypeColumnValues#NUMBERICON} column value.
     */
    NUMBERICON(Character_TypeColumnValues.NUMBERICON),

    /**
     * A constant for the {@value Character_TypeColumnValues#OCTOPUS_PERSON} column value.
     */
    OCTOPUS_PERSON(Character_TypeColumnValues.OCTOPUS_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#OLD_AMAZONS} column value.
     */
    OLD_AMAZONS(Character_TypeColumnValues.OLD_AMAZONS),

    /**
     * A constant for the {@value Character_TypeColumnValues#OMNISCIENT_BEING} column value.
     */
    OMNISCIENT_BEING(Character_TypeColumnValues.OMNISCIENT_BEING),

    /**
     * A constant for the {@value Character_TypeColumnValues#ORGANIC_GUN} column value.
     */
    ORGANIC_GUN(Character_TypeColumnValues.ORGANIC_GUN),

    /**
     * A constant for the {@value Character_TypeColumnValues#PARASITE} column value.
     */
    PARASITE(Character_TypeColumnValues.PARASITE),

    /**
     * A constant for the {@value Character_TypeColumnValues#PASSING_BUTTER_ROBOT} column value.
     */
    PASSING_BUTTER_ROBOT(Character_TypeColumnValues.PASSING_BUTTER_ROBOT),

    /**
     * A constant for the {@value Character_TypeColumnValues#PHONE} column value.
     */
    PHONE(Character_TypeColumnValues.PHONE),

    /**
     * A constant for the {@value Character_TypeColumnValues#PHONE_PERSON} column value.
     */
    PHONE_PERSON(Character_TypeColumnValues.PHONE_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#PICKLE} column value.
     */
    PICKLE(Character_TypeColumnValues.PICKLE),

    /**
     * A constant for the {@value Character_TypeColumnValues#PIZZA} column value.
     */
    PIZZA(Character_TypeColumnValues.PIZZA),

    /**
     * A constant for the {@value Character_TypeColumnValues#PLANET} column value.
     */
    PLANET(Character_TypeColumnValues.PLANET),

    /**
     * A constant for the {@value Character_TypeColumnValues#PLUTONIAN} column value.
     */
    PLUTONIAN(Character_TypeColumnValues.PLUTONIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#PRIPUDLIAN} column value.
     */
    PRIPUDLIAN(Character_TypeColumnValues.PRIPUDLIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#RAT} column value.
     */
    RAT(Character_TypeColumnValues.RAT),

    /**
     * A constant for the {@value Character_TypeColumnValues#RICKS_TOXIC_SIDE} column value.
     */
    RICKS_TOXIC_SIDE(Character_TypeColumnValues.RICKS_TOXIC_SIDE),

    /**
     * A constant for the {@value Character_TypeColumnValues#RING_NIPPLED_ALIEN} column value.
     */
    RING_NIPPLED_ALIEN(Character_TypeColumnValues.RING_NIPPLED_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#ROBOT} column value.
     */
    ROBOT(Character_TypeColumnValues.ROBOT),

    /**
     * A constant for the {@value Character_TypeColumnValues#ROBOT_CROCODILE_HYBRID} column value.
     */
    ROBOT_CROCODILE_HYBRID(Character_TypeColumnValues.ROBOT_CROCODILE_HYBRID),

    /**
     * A constant for the {@value Character_TypeColumnValues#SCARECROW} column value.
     */
    SCARECROW(Character_TypeColumnValues.SCARECROW),

    /**
     * A constant for the {@value Character_TypeColumnValues#SCROTIAN} column value.
     */
    SCROTIAN(Character_TypeColumnValues.SCROTIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SELF_AWARE_ARM} column value.
     */
    SELF_AWARE_ARM(Character_TypeColumnValues.SELF_AWARE_ARM),

    /**
     * A constant for the {@value Character_TypeColumnValues#SENTIENT_ANT_COLONY} column value.
     */
    SENTIENT_ANT_COLONY(Character_TypeColumnValues.SENTIENT_ANT_COLONY),

    /**
     * A constant for the {@value Character_TypeColumnValues#SEXY_AQUAMAN} column value.
     */
    SEXY_AQUAMAN(Character_TypeColumnValues.SEXY_AQUAMAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SHAPESHIFTER} column value.
     */
    SHAPESHIFTER(Character_TypeColumnValues.SHAPESHIFTER),

    /**
     * A constant for the {@value Character_TypeColumnValues#SHIMSHAMIAN} column value.
     */
    SHIMSHAMIAN(Character_TypeColumnValues.SHIMSHAMIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SHRIMP} column value.
     */
    SHRIMP(Character_TypeColumnValues.SHRIMP),

    /**
     * A constant for the {@value Character_TypeColumnValues#SLARTIVARTIAN} column value.
     */
    SLARTIVARTIAN(Character_TypeColumnValues.SLARTIVARTIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SLUG} column value.
     */
    SLUG(Character_TypeColumnValues.SLUG),

    /**
     * A constant for the {@value Character_TypeColumnValues#SNAIL_ALIEN} column value.
     */
    SNAIL_ALIEN(Character_TypeColumnValues.SNAIL_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SNAKE} column value.
     */
    SNAKE(Character_TypeColumnValues.SNAKE),

    /**
     * A constant for the {@value Character_TypeColumnValues#SOULLESS_PUPPET} column value.
     */
    SOULLESS_PUPPET(Character_TypeColumnValues.SOULLESS_PUPPET),

    /**
     * A constant for the {@value Character_TypeColumnValues#SQUID} column value.
     */
    SQUID(Character_TypeColumnValues.SQUID),

    /**
     * A constant for the {@value Character_TypeColumnValues#STARFISH} column value.
     */
    STARFISH(Character_TypeColumnValues.STARFISH),

    /**
     * A constant for the {@value Character_TypeColumnValues#STAIR_GOBLIN} column value.
     */
    STAIR_GOBLIN(Character_TypeColumnValues.STAIR_GOBLIN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SUMMON} column value.
     */
    SUMMON(Character_TypeColumnValues.SUMMON),

    /**
     * A constant for the {@value Character_TypeColumnValues#SUPER_SPERM_MONSTER} column value.
     */
    SUPER_SPERM_MONSTER(Character_TypeColumnValues.SUPER_SPERM_MONSTER),

    /**
     * A constant for the {@value Character_TypeColumnValues#SUPERHUMAN} column value.
     */
    SUPERHUMAN(Character_TypeColumnValues.SUPERHUMAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#SUPERHUMAN_GHOST_TRAINS_SUMMONER} column value.
     */
    SUPERHUMAN_GHOST_TRAINS_SUMMONER(Character_TypeColumnValues.SUPERHUMAN_GHOST_TRAINS_SUMMONER),

    /**
     * A constant for the {@value Character_TypeColumnValues#TEDDY_BEAR} column value.
     */
    TEDDY_BEAR(Character_TypeColumnValues.TEDDY_BEAR),

    /**
     * A constant for the {@value Character_TypeColumnValues#TEENYVERSE_INHABITANT} column value.
     */
    TEENYVERSE_INHABITANT(Character_TypeColumnValues.TEENYVERSE_INHABITANT),

    /**
     * A constant for the {@value Character_TypeColumnValues#TENTACLE_ALIEN} column value.
     */
    TENTACLE_ALIEN(Character_TypeColumnValues.TENTACLE_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#THE_DEVIL} column value.
     */
    THE_DEVIL(Character_TypeColumnValues.THE_DEVIL),

    /**
     * A constant for the {@value Character_TypeColumnValues#TIGER} column value.
     */
    TIGER(Character_TypeColumnValues.TIGER),

    /**
     * A constant for the {@value Character_TypeColumnValues#TIME_GOD} column value.
     */
    TIME_GOD(Character_TypeColumnValues.TIME_GOD),

    /**
     * A constant for the {@value Character_TypeColumnValues#TINYMOUTH} column value.
     */
    TINYMOUTH(Character_TypeColumnValues.TINYMOUTH),

    /**
     * A constant for the {@value Character_TypeColumnValues#TOY} column value.
     */
    TOY(Character_TypeColumnValues.TOY),

    /**
     * A constant for the {@value Character_TypeColumnValues#TRAFLORKIAN} column value.
     */
    TRAFLORKIAN(Character_TypeColumnValues.TRAFLORKIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#TRUNK_PERSON} column value.
     */
    TRUNK_PERSON(Character_TypeColumnValues.TRUNK_PERSON),

    /**
     * A constant for the {@value Character_TypeColumnValues#TUMBLORKIAN} column value.
     */
    TUMBLORKIAN(Character_TypeColumnValues.TUMBLORKIAN),

    /**
     * A constant for the {@value Character_TypeColumnValues#TURKEY} column value.
     */
    TURKEY(Character_TypeColumnValues.TURKEY),

    /**
     * A constant for the {@value Character_TypeColumnValues#TURKEY_HUMAN_MIX} column value.
     */
    TURKEY_HUMAN_MIX(Character_TypeColumnValues.TURKEY_HUMAN_MIX),

    /**
     * A constant for the {@value Character_TypeColumnValues#TUSKFISH} column value.
     */
    TUSKFISH(Character_TypeColumnValues.TUSKFISH),

    /**
     * A constant for the {@value Character_TypeColumnValues#UNKNOWN_NIPPLED_ALIEN} column value.
     */
    UNKNOWN_NIPPLED_ALIEN(Character_TypeColumnValues.UNKNOWN_NIPPLED_ALIEN),

    /**
     * A constant for the {@value Character_TypeColumnValues#VAMPIRE} column value.
     */
    VAMPIRE(Character_TypeColumnValues.VAMPIRE),

    /**
     * A constant for the {@value Character_TypeColumnValues#WASP} column value.
     */
    WASP(Character_TypeColumnValues.WASP),

    /**
     * A constant for the {@value Character_TypeColumnValues#WEASEL} column value.
     */
    WEASEL(Character_TypeColumnValues.WEASEL),

    /**
     * A constant for the {@value Character_TypeColumnValues#WHENWOLF} column value.
     */
    WHENWOLF(Character_TypeColumnValues.WHENWOLF),

    /**
     * A constant for the {@value Character_TypeColumnValues#ZEUS} column value.
     */
    ZEUS(Character_TypeColumnValues.ZEUS),

    /**
     * A constant for the {@value Character_TypeColumnValues#ZIGERION} column value.
     */
    ZIGERION(Character_TypeColumnValues.ZIGERION),

    /**
     * A constant for the {@value Character_TypeColumnValues#ZOMBODIAN} column value.
     */
    ZOMBODIAN(Character_TypeColumnValues.ZOMBODIAN);

    // -------------------------------------------------------------------------------------------------------------
    public static Character_Type valueOfColumnValue(final String columnValue) {
        Objects.requireNonNull(columnValue, "columnValue is null");
        for (final var value : values()) {
            if (value.columnValue.equals(columnValue)) {
                return value;
            }
        }
        throw new IllegalArgumentException("no value for column value: " + columnValue);
    }

    // -------------------------------------------------------------------------------------------------------------
    Character_Type(final String columnValue) {
        this.columnValue = Objects.requireNonNull(columnValue, "columnValue is null");
    }

    // -------------------------------------------------------------------------------------------------------------
    public String columnValue() {
        return columnValue;
    }

    // -------------------------------------------------------------------------------------------------------------
    private final String columnValue;
}
