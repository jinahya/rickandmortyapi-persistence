package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

/**
 * Constants for the {@value Character_#TYPE} attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum Character_Type implements _StringColumnEnum<Character_Type> {

    /**
     * A constant for the {@code "Alligator-Person"} column value.
     */
    ALLIGATOR_PERSON("Alligator-Person"),

    /**
     * A constant for the {@code "Alphabetrian"} column value.
     */
    ALPHABETRIAN("Alphabetrian"),

    /**
     * A constant for the {@code "Amoeba-Person"} column value.
     */
    AMOEBA_PERSON("Amoeba-Person"),

    /**
     * A constant for the {@code "Animal"} column value.
     */
    ANIMAL("Animal"),

    /**
     * A constant for the {@code "Anime"} column value.
     */
    ANIME("Anime"),

    /**
     * A constant for the {@code "Artificial Intelligence"} column value.
     */
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),

    /**
     * A constant for the {@code "Bepisian"} column value.
     */
    BEPISIAN("Bepisian"),

    /**
     * A constant for the {@code "Bird-Person"} column value.
     */
    BIRD_PERSON("Bird-Person"),

    /**
     * A constant for the {@code "Bird-Person Human Mix"} column value.
     */
    BIRD_PERSON_HUMAN_MIX("Bird-Person Human Mix"),

    /**
     * A constant for the {@code "Blue ape alien"} column value.
     */
    BLUE_APE_ALIEN("Blue ape alien"),

    /**
     * A constant for the {@code "Boobie buyer reptilian"} column value.
     */
    BOOBLE_BUYER_REPTILIAN("Boobie buyer reptilian"),

    /**
     * A constant for the {@code "Boobloosian"} column value.
     */
    BOOBLOOSIAN("Boobloosian"),

    /**
     * A constant for the {@code "Bread"} column value.
     */
    BREAD("Bread"),

    /**
     * A constant for the {@code "Cat"} column value.
     */
    CAT("Cat"),

    /**
     * A constant for the {@code "Cat controlled dead lady"} column value.
     */
    CAT_CONTROLLED_DEAD_LADY("Cat controlled dead lady"),

    /**
     * A constant for the {@code "Cat-Person"} column value.
     */
    CAT_PERSON("Cat-Person"),

    /**
     * A constant for the {@code "Caterpillar"} column value.
     */
    CATERPILLAR("Caterpillar"),

    /**
     * A constant for the {@code "Centaur"} column value.
     */
    CENTAUR("Centaur"),

    /**
     * A constant for the {@code "Chair"} column value.
     */
    CHAIR("Chair"),

    /**
     * A constant for the {@code "Changeformer"} column value.
     */
    CHANGEFORMER("Changeformer"),

    /**
     * A constant for the {@code "CHUD"} column value.
     */
    CHUD("CHUD"),

    /**
     * A constant for the {@code "CHUD Human Mix"} column value.
     */
    CHUD_HUMAN_MIX("CHUD Human Mix"),

    /**
     * A constant for the {@code "Clay-Person"} column value.
     */
    CLAY_PERSON("Clay-Person"),

    /**
     * A constant for the {@code "Clone"} column value.
     */
    CLONE("Clone"),

    /**
     * A constant for the {@code "Cone-nippled alien"} column value.
     */
    CONE_NIPPLED_ALIEN("Cone-nippled alien"),

    /**
     * A constant for the {@code "Conjoined twin"} column value.
     */
    CONJOINED_TWIN("Conjoined twin"),

    /**
     * A constant for the {@code "Cookie"} column value.
     */
    COOKIE("Cookie"),

    /**
     * A constant for the {@code "Corn-person"} column value.
     */
    CORN_PERSON("Corn-person"),

    /**
     * A constant for the {@code "Cromulon"} column value.
     */
    CROMULON("Cromulon"),

    /**
     * A constant for the {@code "Cronenberg"} column value.
     */
    CRONENBERG("Cronenberg"),

    /**
     * A constant for the {@code "Crow"} column value.
     */
    CROW("Crow"),

    /**
     * A constant for the {@code "Crow Horse"} column value.
     */
    CROW_HORSE("Crow Horse"),

    /**
     * A constant for the {@code "Cyborg"} column value.
     */
    CYBORG("Cyborg"),

    /**
     * A constant for the {@code "Decoy"} column value.
     */
    DECOY("Decoy"),

    /**
     * A constant for the {@code "Demon"} column value.
     */
    DEMON("Demon"),

    /**
     * A constant for the {@code "Dog"} column value.
     */
    DOG("Dog"),

    /**
     * A constant for the {@code "Doopidoo"} column value.
     */
    DOOPIDOO("Doopidoo"),

    /**
     * A constant for the {@code "Dragon"} column value.
     */
    DRAGON("Dragon"),

    /**
     * A constant for the {@code "Drumbloxian"} column value.
     */
    DRUMBLOXIAN("Drumbloxian"),

    /**
     * A constant for the {@code "Dummy"} column value.
     */
    DUMMY("Dummy"),

    /**
     * A constant for the {@code "Eat shiter-Person"} column value.
     */
    EAT_SHITER_PERSON("Eat shiter-Person"),

    /**
     * A constant for the {@code "Eel"} column value.
     */
    EEL("Eel"),

    /**
     * A constant for the {@code "Elephant-Person"} column value.
     */
    ELEPHANT_PERSON("Elephant-Person"),

    /**
     * A constant for the {@code "Ferkusian"} column value.
     */
    FERKUSIAN("Ferkusian"),

    /**
     * A constant for the {@code "Ferret Robot"} column value.
     */
    FERRET_ROBOT("Ferret Robot"),

    /**
     * A constant for the {@code "Fish-Person"} column value.
     */
    FISH_PERSON("Fish-Person"),

    /**
     * A constant for the {@code "Flansian"} column value.
     */
    FLANSIAN("Flansian"),

    /**
     * A constant for the {@code "Floop Floopian"} column value.
     */
    FLOOP_FLOOPIAN("Floop Floopian"),

    /**
     * A constant for the {@code "Fly"} column value.
     */
    FLY("Fly"),

    /**
     * A constant for the {@code "Game"} column value.
     */
    GAME("Game"),

    /**
     * A constant for the {@code "Garblovian"} column value.
     */
    GARBLOVIAN("Garblovian"),

    /**
     * A constant for the {@code "Gazorpian"} column value.
     */
    GAZORPIAN("Gazorpian"),

    /**
     * A constant for the {@code "Gazorpian reproduction robot"} column value.
     */
    GAZORPIAN_REPRODUCTION_ROBOT("Gazorpian reproduction robot"),

    /**
     * A constant for the {@code "Gear-Person"} column value.
     */
    GEAR_PERSON("Gear-Person"),

    /**
     * A constant for the {@code "Genetic experiment"} column value.
     */
    GENETIC_EXPERIMENT("Genetic experiment"),

    /**
     * A constant for the {@code "Giant"} column value.
     */
    GIANT("Giant"),

    /**
     * A constant for the {@code "Giant Cat Monster"} column value.
     */
    GIANT_CAT_MONSTER("Giant Cat Monster"),

    /**
     * A constant for the {@code "Giant Incest Baby"} column value.
     */
    GIANT_INCEST_BABY("Giant Incest Baby"),

    /**
     * A constant for the {@code "Glorzo"} column value.
     */
    GLORZO("Glorzo"),

    /**
     * A constant for the {@code "God"} column value.
     */
    GOD("God"),

    /**
     * A constant for the {@code "Goddess"} column value.
     */
    GODDESS("Goddess"),

    /**
     * A constant for the {@code "Gramuflackian"} column value.
     */
    GRAMUFLAKIAN("Gramuflackian"),

    /**
     * A constant for the {@code "Grandma"} column value.
     */
    GRANDMA("Grandma"),

    /**
     * A constant for the {@code "Greebybobe"} column value.
     */
    GREEBYBOBE("Greebybobe"),

    /**
     * A constant for the {@code "Gromflomite"} column value.
     */
    GROMFLOMITE("Gromflomite"),

    /**
     * A constant for the {@code "Guinea Pig for the Polio Vaccine"} column value.
     */
    GUINEA_PIG_FOR_THE_POLIO_VACCINE("Guinea Pig for the Polio Vaccine"),

    /**
     * A constant for the {@code "Hairy alien"} column value.
     */
    HAIRY_ALIEN("Hairy alien"),

    /**
     * A constant for the {@code "Half Soulless Puppet"} column value.
     */
    HALF_SOULLESS_PUPPET("Half Soulless Puppet"),

    /**
     * A constant for the {@code "Hammerhead-Person"} column value.
     */
    HAMMERHEAD_PERSON("Hammerhead-Person"),

    /**
     * A constant for the {@code "Hivemind"} column value.
     */
    HIVEMIND("Hivemind"),

    /**
     * A constant for the {@code "Hole"} column value.
     */
    HOLE("Hole"),

    /**
     * A constant for the {@code "Hologram"} column value.
     */
    HOLOGRAM("Hologram"),

    /**
     * A constant for the {@code "Human Gazorpian"} column value.
     */
    HUMAN_GAZORPIAN("Human Gazorpian"),

    /**
     * A constant for the {@code "Human-Snake hybrid"} column value.
     */
    HUMAN_SNAKE_HYBRID("Human-Snake hybrid"),

    /**
     * A constant for the {@code "Human with a flower in his head"} column value.
     */
    HUMAN_WITH_A_FLOWER_IN_HIS_HEAD("Human with a flower in his head"),

    /**
     * A constant for the {@code "Human with antennae"} column value.
     */
    HUMAN_WITH_ANTENNAE("Human with antennae"),

    /**
     * A constant for the {@code "Human with ants in his eyes"} column value.
     */
    HUMAN_WITH_ANTS_IN_HIS_EYES("Human with ants in his eyes"),

    /**
     * A constant for the {@code "Human with baby legs"} column value.
     */
    HUMAN_WITH_BABY_LEGS("Human with baby legs"),

    /**
     * A constant for the {@code "Human with giant head"} column value.
     */
    HUMAN_WITH_GIANT_HEAD("Human with giant head"),

    /**
     * A constant for the {@code "Human with tusks"} column value.
     */
    HUMAN_WITH_TUSKS("Human with tusks"),

    /**
     * A constant for the {@code "Interdimensional gaseous being"} column value.
     */
    INTERDIMENSIONAL_GASEOUS_BEING("Interdimensional gaseous being"),

    /**
     * A constant for the {@code "Jellybean"} column value.
     */
    JELLYBEAN("Jellybean"),

    /**
     * A constant for the {@code "Korblock"} column value.
     */
    KORBLOCK("Korblock"),

    /**
     * A constant for the {@code "Krootabulan"} column value.
     */
    KROOTABULAN("Krootabulan"),

    /**
     * A constant for the {@code "Larva alien"} column value.
     */
    LARVA_ALIEN("Larva alien"),

    /**
     * A constant for the {@code "Leprechaun"} column value.
     */
    LEPRECHAUN("Leprechaun"),

    /**
     * A constant for the {@code "Light bulb-Alien"} column value.
     */
    LIGHT_BULB_ALIEN("Light bulb-Alien"),

    /**
     * A constant for the {@code "Little Human"} column value.
     */
    LITTLE_HUMAN("Little Human"),

    /**
     * A constant for the {@code "Lizard"} column value.
     */
    LIZARD("Lizard"),

    /**
     * A constant for the {@code "Lizard-Person"} column value.
     */
    LIZARD_PERSON("Lizard-Person"),

    /**
     * A constant for the {@code "Lobster-Alien"} column value.
     */
    LOBSTER_ALIEN("Lobster-Alien"),

    /**
     * A constant for the {@code "Mannie"} column value.
     */
    MANNIE("Mannie"),

    /**
     * A constant for the {@code "Mascot"} column value.
     */
    MASCOT("Mascot"),

    /**
     * A constant for the {@code "Meeseeks"} column value.
     */
    MEESEEKS("Meeseeks"),

    /**
     * A constant for the {@code "Mega Gargantuan"} column value.
     */
    MEGA_GARGANTUAN("Mega Gargantuan"),

    /**
     * A constant for the {@code "Memory"} column value.
     */
    MEMORY("Memory"),

    /**
     * A constant for the {@code "Mexican"} column value.
     */
    MEXICAN("Mexican"),

    /**
     * A constant for the {@code "Microverse inhabitant"} column value.
     */
    MICROVERSE_INHABITANT("Microverse inhabitant"),

    /**
     * A constant for the {@code "Miniverse inhabitant"} column value.
     */
    MINIVERSE_INHABITANT("Miniverse inhabitant"),

    /**
     * A constant for the {@code "Monogatron"} column value.
     */
    MONOGATRON("Monogatron"),

    /**
     * A constant for the {@code "Monster"} column value.
     */
    MONSTER("Monster"),

    /**
     * A constant for the {@code "Morglutzian"} column value.
     */
    MORGLUTZIAN("Morglutzian"),

    /**
     * A constant for the {@code "Morty's toxic side"} column value.
     */
    MORTYS_TOXIC_SIDE("Morty's toxic side"),

    /**
     * A constant for the {@code "Mytholog"} column value.
     */
    MYTHOLOG("Mytholog"),

    /**
     * A constant for the {@code "Nano Alien"} column value.
     */
    NANO_ALIEN("Nano Alien"),

    /**
     * A constant for the {@code "Narnian"} column value.
     */
    NARNIAN("Narnian"),

    /**
     * A constant for the {@code "Necrophiliac"} column value.
     */
    NECROPHILIAC("Necrophiliac"),

    /**
     * A constant for the {@code "Normal Size Bug"} column value.
     */
    NORMAL_SIZE_BUG("Normal Size Bug"),

    /**
     * A constant for the {@code "Numbericon"} column value.
     */
    NUMBERICON("Numbericon"),

    /**
     * A constant for the {@code "Octopus-Person"} column value.
     */
    OCTOPUS_PERSON("Octopus-Person"),

    /**
     * A constant for the {@code "Old Amazons"} column value.
     */
    OLD_AMAZONS("Old Amazons"),

    /**
     * A constant for the {@code "Omniscient being"} column value.
     */
    OMNISCIENT_BEING("Omniscient being"),

    /**
     * A constant for the {@code "Organic gun"} column value.
     */
    ORGANIC_GUN("Organic gun"),

    /**
     * A constant for the {@code "Parasite"} column value.
     */
    PARASITE("Parasite"),

    /**
     * A constant for the {@code "Passing Butter Robot"} column value.
     */
    PASSING_BUTTER_ROBOT("Passing Butter Robot"),

    /**
     * A constant for the {@code "Phone"} column value.
     */
    PHONE("Phone"),

    /**
     * A constant for the {@code "Phone-Person"} column value.
     */
    PHONE_PERSON("Phone-Person"),

    /**
     * A constant for the {@code "Pickle"} column value.
     */
    PICKLE("Pickle"),

    /**
     * A constant for the {@code "Pizza"} column value.
     */
    PIZZA("Pizza"),

    /**
     * A constant for the {@code "Planet"} column value.
     */
    PLANET("Planet"),

    /**
     * A constant for the {@code "Plutonian"} column value.
     */
    PLUTONIAN("Plutonian"),

    /**
     * A constant for the {@code "Pripudlian"} column value.
     */
    PRIPUDLIAN("Pripudlian"),

    /**
     * A constant for the {@code "Rat"} column value.
     */
    RAT("Rat"),

    /**
     * A constant for the {@code "Rick's toxic side"} column value.
     */
    RICKS_TOXIC_SIDE("Rick's toxic side"),

    /**
     * A constant for the {@code "Ring-nippled alien"} column value.
     */
    RING_NIPPLED_ALIEN("Ring-nippled alien"),

    /**
     * A constant for the {@code "Robot"} column value.
     */
    ROBOT("Robot"),

    /**
     * A constant for the {@code "Robot-Crocodile hybrid"} column value.
     */
    ROBOT_CROCODILE_HYBRID("Robot-Crocodile hybrid"),

    /**
     * A constant for the {@code "Scarecrow"} column value.
     */
    SCARECROW("Scarecrow"),

    /**
     * A constant for the {@code "Scrotian"} column value.
     */
    SCROTIAN("Scrotian"),

    /**
     * A constant for the {@code "Self-aware arm"} column value.
     */
    SELF_AWARE_ARM("Self-aware arm"),

    /**
     * A constant for the {@code "Sentient ant colony"} column value.
     */
    SENTIENT_ANT_COLONY("Sentient ant colony"),

    /**
     * A constant for the {@code "Sexy Aquaman"} column value.
     */
    SEXY_AQUAMAN("Sexy Aquaman"),

    /**
     * A constant for the {@code "Shapeshifter"} column value.
     */
    SHAPESHIFTER("Shapeshifter"),

    /**
     * A constant for the {@code "Shimshamian"} column value.
     */
    SHIMSHAMIAN("Shimshamian"),

    /**
     * A constant for the {@code "Shrimp"} column value.
     */
    SHRIMP("Shrimp"),

    /**
     * A constant for the {@code "Slartivartian"} column value.
     */
    SLARTIVARTIAN("Slartivartian"),

    /**
     * A constant for the {@code "Slug"} column value.
     */
    SLUG("Slug"),

    /**
     * A constant for the {@code "Snail alien"} column value.
     */
    SNAIL_ALIEN("Snail alien"),

    /**
     * A constant for the {@code "Snake"} column value.
     */
    SNAKE("Snake"),

    /**
     * A constant for the {@code "Soulless Puppet"} column value.
     */
    SOULLESS_PUPPET("Soulless Puppet"),

    /**
     * A constant for the {@code "Squid"} column value.
     */
    SQUID("Squid"),

    /**
     * A constant for the {@code "Starfish"} column value.
     */
    STARFISH("Starfish"),

    /**
     * A constant for the {@code "Stair goblin"} column value.
     */
    STAIR_GOBLIN("Stair goblin"),

    /**
     * A constant for the {@code "Summon"} column value.
     */
    SUMMON("Summon"),

    /**
     * A constant for the {@code "Super Sperm Monster"} column value.
     */
    SUPER_SPERM_MONSTER("Super Sperm Monster"),

    /**
     * A constant for the {@code "Superhuman"} column value.
     */
    SUPERHUMAN("Superhuman"),

    /**
     * A constant for the {@code "Superhuman (Ghost trains summoner)"} column value.
     */
    SUPERHUMAN_GHOST_TRAINS_SUMMONER("Superhuman (Ghost trains summoner)"),

    /**
     * A constant for the {@code "Teddy Bear"} column value.
     */
    TEDDY_BEAR("Teddy Bear"),

    /**
     * A constant for the {@code "Teenyverse inhabitant"} column value.
     */
    TEENYVERSE_INHABITANT("Teenyverse inhabitant"),

    /**
     * A constant for the {@code "Tentacle alien"} column value.
     */
    TENTACLE_ALIEN("Tentacle alien"),

    /**
     * A constant for the {@code "The Devil"} column value.
     */
    THE_DEVIL("The Devil"),

    /**
     * A constant for the {@code "Tiger"} column value.
     */
    TIGER("Tiger"),

    /**
     * A constant for the {@code "Time God"} column value.
     */
    TIME_GOD("Time God"),

    /**
     * A constant for the {@code "Tinymouth"} column value.
     */
    TINYMOUTH("Tinymouth"),

    /**
     * A constant for the {@code "Toy"} column value.
     */
    TOY("Toy"),

    /**
     * A constant for the {@code "Traflorkian"} column value.
     */
    TRAFLORKIAN("Traflorkian"),

    /**
     * A constant for the {@code "Trunk-Person"} column value.
     */
    TRUNK_PERSON("Trunk-Person"),

    /**
     * A constant for the {@code "Tumblorkian"} column value.
     */
    TUMBLORKIAN("Tumblorkian"),

    /**
     * A constant for the {@code "Turkey"} column value.
     */
    TURKEY("Turkey"),

    /**
     * A constant for the {@code "Turkey Human Mix"} column value.
     */
    TURKEY_HUMAN_MIX("Turkey Human Mix"),

    /**
     * A constant for the {@code "Tuskfish"} column value.
     */
    TUSKFISH("Tuskfish"),

    /**
     * A constant for the {@code "Unknown-nippled alien"} column value.
     */
    UNKNOWN_NIPPLED_ALIEN("Unknown-nippled alien"),

    /**
     * A constant for the {@code "Vampire"} column value.
     */
    VAMPIRE("Vampire"),

    /**
     * A constant for the {@code "Wasp"} column value.
     */
    WASP("Wasp"),

    /**
     * A constant for the {@code "Weasel"} column value.
     */
    WEASEL("Weasel"),

    /**
     * A constant for the {@code "Whenwolf"} column value.
     */
    WHENWOLF("Whenwolf"),

    /**
     * A constant for the {@code "Zeus"} column value.
     */
    ZEUS("Zeus"),

    /**
     * A constant for the {@code "Zigerion"} column value.
     */
    ZIGERION("Zigerion"),

    /**
     * A constant for the {@code "Zombodian"} column value.
     */
    ZOMBODIAN("Zombodian");

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
