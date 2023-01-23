package tai.tutorial.proietti.platformsample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.alfresco.service.namespace.QName;

public interface Parametri {
	
    public static final String URI ="http://it.tai.proietti.tutorial/civ/content/model/1.0";
    
    
    static final String CONTENT_MODEL = "civContentModel";
    public static final QName CIV_CONTENT_MODEL = QName.createQName(URI, CONTENT_MODEL);

    
    
    //CIV:TYPES
    static final String TYPE_LEADER = "leader";
    public static final QName CIV_TYPE_LEADER = QName.createQName(URI, TYPE_LEADER);
    
    //PROPERTIES
    
    static final String PROP_LEADER_NAME = "leaderName";
    static final QName CIV_PROP_LEADER_NAME = QName.createQName(URI, PROP_LEADER_NAME);
    
    
    //CIV:TYPES
    
    static final String TYPE_CIVILIZATION = "civilization";
    static final QName CIV_TYPE_CIVILIZATION = QName.createQName(URI, TYPE_CIVILIZATION);
    
    
    //PROPERTIES
    
    static final String PROP_CIVILIZATION_NAME = "civilizationName";
    static final QName CIV_PROP_CIVILIZATION_NAME = QName.createQName(URI, PROP_CIVILIZATION_NAME);
    
    
    //CIV:ASPECTS
    static final String ASPECT_UNIQUE_ABILITIES = "uniqueAbilities";
    static final QName CIV_ASPECT_UNIQUE_ABILITIES = QName.createQName(URI, ASPECT_UNIQUE_ABILITIES);
    
    //PROPERTIES
    
    static final String PROP_UNIQUE_ABILITIES_NAME = "uniqueAbilitiesName";
    static final QName CIV_PROP_UNIQUE_ABILITIES_NAME = QName.createQName(URI, PROP_UNIQUE_ABILITIES_NAME);
    
    static final String PROP_UNIQUE_ABILITIES_BONUS = "uniqueAbilitiesBonus";
    static final QName CIV_PROP_UNIQUE_ABILITIES_BONUS = QName.createQName(URI, PROP_UNIQUE_ABILITIES_BONUS);
    
    //CIV:ASPECTS
    
    static final String ASPECT_UNIQUE_STRUCTURES = "uniqueStructures";
    static final QName CIV_ASPECT_UNIQUE_STRUCTURES = QName.createQName(URI, ASPECT_UNIQUE_STRUCTURES);
    
    //PROPERTIES
    
    static final String PROP_UNIQUE_STRUCTURES_NAME = "uniqueStructuresName";
    static final QName CIV_PROP_UNIQUE_STRUCTURES_NAME = QName.createQName(URI, PROP_UNIQUE_STRUCTURES_NAME);
    
    static final String PROP_UNIQUE_STRUCTURES_BONUS = "uniqueStructuresBonus";
    static final QName CIV_PROP_UNIQUE_STRUCTURES_BONUS = QName.createQName(URI, PROP_UNIQUE_STRUCTURES_BONUS);
    
    static final String PROP_UNIQUE_STRUCTURES_AGES = "uniqueStructuresAges";
    static final QName CIV_PROP_UNIQUE_STRUCTURES_AGES = QName.createQName(URI, PROP_UNIQUE_STRUCTURES_AGES);
    
    //CIV:ASPECTS
    
    static final String ASPECT_UNIQUE_UNITS= "uniqueUnits";
    static final QName CIV_ASPECT_UNIQUE_UNITS = QName.createQName(URI, ASPECT_UNIQUE_UNITS);
    
    
    //PROPERTIES
    
    static final String PROP_UNIQUE_UNITS_NAME = "uniqueUnitsName";
    static final QName CIV_PROP_UNIQUE_UNITS_NAME = QName.createQName(URI, PROP_UNIQUE_UNITS_NAME);
    
    static final String PROP_UNIQUE_UNITS_BONUS = "uniqueUnitsBonus";
    static final QName CIV_PROP_UNIQUE_UNITS_BONUS = QName.createQName(URI, PROP_UNIQUE_UNITS_BONUS);
    
    static final String PROP_UNIQUE_UNITS_AGES = "uniqueUnitsAges";
    static final QName CIV_PROP_UNIQUE_UNITS_AGES = QName.createQName(URI, PROP_UNIQUE_UNITS_AGES);
    
    
    //CONSTRAINTS
    
    static final String CONSTRAINT_NAME = "ages";
    static final QName CIV_CONSTRAINT_NAME = QName.createQName(URI, CONSTRAINT_NAME);
    
    static final String CONSTRAINT_ANCIENT_ERA = "Ancient Era";
    static final QName CIV_CONSTRAINT_ANCIENT_ERA = QName.createQName(URI, CONSTRAINT_ANCIENT_ERA);
    
    static final String CONSTRAINT_CLASSICAL_ERA = "Classical Era";
    static final QName CIV_CONSTRAINT_CLASSICAL_ERA = QName.createQName(URI, CONSTRAINT_CLASSICAL_ERA);
    
    static final String CONSTRAINT_MEDIEVAL_ERA = "Medieval Era Era";
    static final QName CIV_CONSTRAINT_MEDIEVAL_ERA = QName.createQName(URI, CONSTRAINT_MEDIEVAL_ERA);
    
    static final String CONSTRAINT_REINASSANCE_ERA = "Reinassance Era";
    static final QName CIV_CONSTRAINT_REINASSANCE_ERA = QName.createQName(URI, CONSTRAINT_REINASSANCE_ERA);
    
    static final String CONSTRAINT_INDUSTRIAL_ERA = "Industrial Era";
    static final QName CIV_CONSTRAINT_INDUSTRIAL_ERA = QName.createQName(URI, CONSTRAINT_REINASSANCE_ERA);
    
    static final String CONSTRAINT_MODERN_ERA = "Modern Era";
    static final QName CIV_CONSTRAINT_MODERN_ERA = QName.createQName(URI, CONSTRAINT_MODERN_ERA);
    
    static final String CONSTRAINT_ATOMIC_ERA = "Atomic Era";
    static final QName CIV_CONSTRAINT_ATOMIC_ERA = QName.createQName(URI, CONSTRAINT_ATOMIC_ERA);
    
    static final String CONSTRAINT_INFORMATION_ERA = "Information Era";
    static final QName CIV_CONSTRAINT_INFORMATION_ERA = QName.createQName(URI, CONSTRAINT_INFORMATION_ERA);
    
    static final String CONSTRAINT_FUTURE_ERA = "Future Era";
    static final QName CIV_CONSTRAINT_FUTURE_ERA = QName.createQName(URI, CONSTRAINT_FUTURE_ERA);
    
    
    //CIV:ASSOC
    
    static final String ASSOC_RELATED_LEADERS = "relatedLeaders";
    static final QName CIV_ASSOC_RELATED_LEADERS = QName.createQName(URI, ASSOC_RELATED_LEADERS);
    
    static final String ASSOC_RELATED_CIV = "relatedCiv";
    static final QName CIV_ASSOC_RELATED_CIV = QName.createQName(URI, ASSOC_RELATED_CIV);
    
    
    //CIV:ERROR
    
    static final String ASPECT_ERROR_ASPECT = "errorAspect";
    static final QName CIV_ASPECT_ERROR_ASPECT = QName.createQName(URI, ASPECT_ERROR_ASPECT);

    //CIV:PROPERTIES

    static final String PROP_ERROR_ASPECT_STRING = "errorAspectString";
    static final QName CIV_PROP_ERROR_ASPECT_STRING = QName.createQName(URI, PROP_ERROR_ASPECT_STRING);
    
    
    //CIV:PLAYED CIV ASPECT
    static final String ASPECT_PLAYED_CIV = "playedCiv";
    static final QName CIV_ASPECT_PLAYED_CIV = QName.createQName(URI, ASPECT_PLAYED_CIV);
    
    //CIV:PROPERTIES
    static final String PROP_IS_PLAYED= "isPlayed";
    static final QName CIV_PROP_IS_PLAYED = QName.createQName(URI, PROP_IS_PLAYED);
    
    static final String PROP_NUMBER_OF_TURNS= "numberOfTurns";
    static final QName CIV_PROP_NUMBER_OF_TURNS = QName.createQName(URI, PROP_NUMBER_OF_TURNS);
    
    static final String PROP_TYPE_OF_VICTORY= "typeOfVictory";
    static final QName CIV_PROP_TYPE_OF_VICTORY = QName.createQName(URI, PROP_TYPE_OF_VICTORY);
    
    static final String PROP_DATE_OF_PLAY= "dateOfPlay";
    static final QName CIV_PROP_DATE_OF_PLAY = QName.createQName(URI, PROP_DATE_OF_PLAY);
    
    //TYPE OF VICTORY
    List<String> victoryType = Arrays.asList("scientific","domination","cultural",
    		"religious","diplomatic","points");
    
    
    



}
