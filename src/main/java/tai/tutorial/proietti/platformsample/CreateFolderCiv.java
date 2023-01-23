package tai.tutorial.proietti.platformsample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.repo.dictionary.constraint.ListOfValuesConstraint;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CreateFolderCiv extends ActionExecuterAbstractBase {
	public static final String PARAM_DESTINATION_FOLDER = "destination-folder";
	public static final String PARAM_ERROR_FOLDER = "error-folder";





	private FileFolderService fileFolderService;
	private NodeService nodeService;
	private ServiceRegistry serviceRegistry;
	private FileInfo file;
	private DictionaryService dataDictionary;
	private Map<QName,Serializable> propUniqueUnits = new HashMap<>();
	private Map<QName,Serializable> propUniqueStructures = new HashMap<>();
	private Map<QName,Serializable> propUniqueAbilities = new HashMap<>();
	private Map<QName,Serializable> errorAspectString = new HashMap<>();
	private String UniqueUnitName = null;
	private String UniqueUnitBonus = null;
	private String UniqueUnitAge = null;
	String UniqueAbilitiesName = null;
	String UniqueAbilitiesBonus = null;
	NamespaceService nameSpace;





	private static Log logger = LogFactory.getLog(CreateFolderCiv.class);  

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		paramList.add(
				new ParameterDefinitionImpl(PARAM_DESTINATION_FOLDER,
						DataTypeDefinition.NODE_REF,
						true,
						getParamDisplayLabel(PARAM_DESTINATION_FOLDER)));

		paramList.add(
				new ParameterDefinitionImpl(PARAM_ERROR_FOLDER,
						DataTypeDefinition.NODE_REF,
						true,
						getParamDisplayLabel(PARAM_ERROR_FOLDER)));
	}

	/**
	 * @see org.alfresco.repo.action.executer.ActionExecuter#execute(org.alfresco.repo.ref.NodeRef, org.alfresco.repo.ref.NodeRef)
	 */
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef) {

		NodeRef parameter = (NodeRef)ruleAction.getParameterValue(PARAM_DESTINATION_FOLDER);
		NodeRef errorParameter = (NodeRef)ruleAction.getParameterValue(PARAM_ERROR_FOLDER);

		//RICAVO IL NOME DEL FILE
		String nome = nodeService.getProperty(actionedUponNodeRef,ContentModel.PROP_NAME).toString();
		String nomeSplittato[] = nome.split("_");
		if(nomeSplittato.length < 4) {
			logger.error("Il file è troppo corto");
			moveError(actionedUponNodeRef, errorParameter);
			return;

		}




		//CONTROLLO CHE NESSUNA STRINGA SIA VUOTA

		for(int i = 0;i < nomeSplittato.length;i++) {
			if(nomeSplittato[i] == null || nomeSplittato[i].isEmpty()) {
				logger.error("Una stringa del nome del file è risultata vuota");
				moveError(actionedUponNodeRef, errorParameter);
				return;

			}
		}

		//RICAVO TIPO E NOME DELLA CIVILTA'
		String tipo = nomeSplittato[0];
		String CivilizationName = nomeSplittato[1];



		//DAL NOME DEDUCO IL TIPO
		if(tipo.equalsIgnoreCase("led"))
			nodeService.setType(actionedUponNodeRef,Parametri.CIV_TYPE_LEADER);
		else 
			if(tipo.equalsIgnoreCase("civ"))
				nodeService.setType(actionedUponNodeRef,Parametri.CIV_TYPE_CIVILIZATION);
			else {
				logger.error("Tipo non riconosciuto");
				moveError(actionedUponNodeRef, errorParameter);
				return;
			}





		//SE IL TIPO E' CIVILIZATION CREO, SE NON ESISTE GIA', UNA CARTELLA COL NOME DELLA CIVILITA'.
		if(nodeService.getType(actionedUponNodeRef).equals(Parametri.CIV_TYPE_CIVILIZATION)) {


			//RICAVO I VALORI DELLE PROPRIETA'

			if(nomeSplittato.length >= 4) {
				UniqueAbilitiesName = nomeSplittato[2];
				UniqueAbilitiesBonus = nomeSplittato[3];
			}
			else {
				logger.error("stringa troppo corta");
				moveError(actionedUponNodeRef, errorParameter);
				return;

			}
			
			if(UniqueAbilitiesBonus.contains(".")) {
				String[] app = UniqueAbilitiesBonus.split("[.]");
				if(app[0] != null && !app[0].isEmpty())
				UniqueAbilitiesBonus = app[0];
				else {
					logger.error("Unique Abilities Bonus di " + CivilizationName + " è risultato null");
					moveError(actionedUponNodeRef, errorParameter);
					return;
				}
			}

			//RINOMINO IL FILE COL NOME DELLA CIVILTA'

			try {
				fileFolderService.rename(actionedUponNodeRef, CivilizationName);
			} catch (FileExistsException e) {
				e.printStackTrace();
				logger.error("Esiste già un file col nome di questa civiltà" + e.getMessage());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error("non ho trovato il file da rinominare" + e.getMessage());
			}




			//CREO LA CARTELLA COL NUOVO NOME DELLA CIVILITA'
			if(fileFolderService.searchSimple(parameter,CivilizationName) == null) {
				file = fileFolderService.create(parameter, CivilizationName, ContentModel.TYPE_FOLDER);
				logger.info("Folder per la civiltà " + CivilizationName + " creato");
			}

			//SE ESISTE UNA CARTELLA DI QUELLA CIVILTA' VADO IN ERRORE
			else {
				logger.error("Esiste già una cartella col nome " + CivilizationName);
				moveError(actionedUponNodeRef, errorParameter);
				return;
			}

			//AGGIUNGO GLI ASPETTI TIPICI DELLE CIVILTA'
			//ABILITIES


			



			if(UniqueAbilitiesBonus != null && UniqueAbilitiesName != null) {
				propUniqueAbilities.put(Parametri.CIV_PROP_UNIQUE_ABILITIES_NAME, UniqueAbilitiesName);
				propUniqueAbilities.put(Parametri.CIV_PROP_UNIQUE_ABILITIES_BONUS,UniqueAbilitiesBonus);
				nodeService.addAspect(file.getNodeRef(), Parametri.CIV_ASPECT_UNIQUE_ABILITIES, propUniqueAbilities);
				nodeService.addAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_UNIQUE_ABILITIES, propUniqueAbilities);
			}
			else {
				logger.error("Le stringhe di abilità e bonus sono null");
				moveError(actionedUponNodeRef, errorParameter);
				return;
			}





			//SPOSTO IL FILE NELLA CARTELLA APPENA CREATA
			try {
				fileFolderService.move(actionedUponNodeRef,file.getNodeRef(), null);
				logger.info(CivilizationName + "spostata nella corrispettiva cartella");
			} catch (FileExistsException e) {
				logger.error("Errore nello spostare il file nella cartella civilization appena creata, il file esiste già" + e.getMessage());
			} catch (FileNotFoundException e) {
				logger.error("Errore nello spostare il file nella cartella civilization appena creata, il file non è stato trovato" + e.getMessage());
			}
		}



		//SE IL TIPO E' LEADER 
		else if(nodeService.getType(actionedUponNodeRef).equals(Parametri.CIV_TYPE_LEADER)) {
			NodeRef destinazione = fileFolderService.searchSimple(parameter,CivilizationName);
			String LeadersName = nomeSplittato[2];


			//SE ESISTE UNA CARTELLA COL NOME DELLA CIVILTA' CI SPOSTO IL LEADER 
			if(destinazione != null) {
				try {
					fileFolderService.move(actionedUponNodeRef, destinazione,null);
					logger.info(LeadersName + "spostato nella cartella della seguente cività: " + CivilizationName);
				} catch (FileExistsException e) {
					logger.error("Il file esiste già");
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					logger.error("Il file non esiste");
					e.printStackTrace();
				}

			}
			//SE IL LEADER E' SENZA CIVILITA' LO SPOSTO NELL' ERROR_FOLDER

			else {
				logger.error("Leader senza civiltà");
				moveError(actionedUponNodeRef, errorParameter);
				return;


			}

			//RICAVO I VALORI DELLE PROPRIETA'
			if(nomeSplittato.length >= 6) {
				UniqueUnitName = nomeSplittato[3];
				UniqueUnitBonus = nomeSplittato[4];
			}
			else {
				logger.error("Stringa Leader troppo corta per attribuire la unique unit");
				moveError(actionedUponNodeRef, errorParameter);
				return;

			}

			UniqueUnitAge = nomeSplittato[5];


			if(UniqueUnitAge.contains(".")) {
				String[] app = UniqueUnitAge.split("[.]");
				if(app[0] != null && !app[0].isEmpty())
				UniqueUnitAge = app[0];
				else {
					logger.error("La unique unit age di " + LeadersName + " è risultata vuota");
					moveError(actionedUponNodeRef, errorParameter);
					return;
				}
			}


			List<String> constraints = (List<String>) dataDictionary.getConstraint(Parametri.CIV_CONSTRAINT_NAME).getConstraint()
					.getParameters().get(ListOfValuesConstraint.ALLOWED_VALUES_PARAM);




			if(!constraints.contains(UniqueUnitAge)) {
				logger.error("Constraint sulla unique unit age violato violato");
				moveError(actionedUponNodeRef, errorParameter);
				return;
			}






			//RINOMINO IL FILE
			//SE C'E' GIA' UN FILE CON LO STESSO NOME SPOSTO IN ERRORE
			if(fileFolderService.searchSimple(destinazione, LeadersName) == null) {
				try {
					fileFolderService.rename(actionedUponNodeRef, LeadersName);
				} catch (FileExistsException e) {
					logger.error("file non rinominato perchè già esiste" + e.getMessage());
				} catch (FileNotFoundException e) {
					logger.error("file non rinominato perchè non trovato" + e.getMessage());
				}
			}
			else {
				moveError(actionedUponNodeRef, errorParameter);
				return;

			}



			//AGGIUNGO GLI ASPETTI TIPICI DEI LEADER
			//UNITS
			propUniqueUnits.put(Parametri.CIV_PROP_UNIQUE_UNITS_NAME, UniqueUnitName);
			propUniqueUnits.put(Parametri.CIV_PROP_UNIQUE_UNITS_BONUS,UniqueUnitBonus);
			propUniqueUnits.put(Parametri.CIV_PROP_UNIQUE_UNITS_AGES,UniqueUnitAge);
			nodeService.addAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_UNIQUE_UNITS, propUniqueUnits);

			//AGGIUNGO LE ASSOCIAZIONI CUSTOM
			NodeRef childByName = nodeService.getChildByName(destinazione,ContentModel.ASSOC_CONTAINS, CivilizationName);
			nodeService.createAssociation(actionedUponNodeRef,childByName, Parametri.CIV_ASSOC_RELATED_CIV);
			nodeService.createAssociation(childByName, actionedUponNodeRef, Parametri.CIV_ASSOC_RELATED_LEADERS);

			//AGGIUNGO GLI ASPETTI DEL PARENT
			propUniqueAbilities.put(Parametri.CIV_PROP_UNIQUE_ABILITIES_NAME, nodeService.getProperty(destinazione, Parametri.CIV_PROP_UNIQUE_ABILITIES_NAME));
			propUniqueAbilities.put(Parametri.CIV_PROP_UNIQUE_ABILITIES_BONUS, nodeService.getProperty(destinazione, Parametri.CIV_PROP_UNIQUE_ABILITIES_BONUS));
			nodeService.addAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_UNIQUE_ABILITIES, propUniqueAbilities);


		}


		//			//STRUCTURES
		//			propUniqueStructures.put(Parametri.CIV_PROP_UNIQUE_STRUCTURES_NAME, new String());
		//			propUniqueStructures.put(Parametri.CIV_PROP_UNIQUE_STRUCTURES_BONUS, new String());
		//			propUniqueStructures.put(Parametri.CIV_PROP_UNIQUE_STRUCTURES_AGES, new String());
		//			nodeService.addAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_UNIQUE_STRUCTURES, propUniqueStructures);
		//			

	}

	private void moveError(NodeRef actionedUponNodeRef, NodeRef errorParameter) {
		try {
			String nome = nodeService.getProperty(actionedUponNodeRef,ContentModel.PROP_NAME).toString();
			int cont = 1;
			if(fileFolderService.searchSimple(errorParameter,nome + cont) == null ) {
				fileFolderService.move(actionedUponNodeRef, errorParameter,null);

			}
			else {
				while(fileFolderService.searchSimple(errorParameter,nome + cont) != null){
					cont++;

				}
			}
			fileFolderService.rename(actionedUponNodeRef, nome + cont);
			fileFolderService.move(actionedUponNodeRef, errorParameter,null);
			String error = new String("errore");
			errorAspectString.put(Parametri.CIV_PROP_ERROR_ASPECT_STRING,error);
			nodeService.addAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_ERROR_ASPECT, errorAspectString);


		} catch (FileExistsException e) {
			logger.error("Il file esiste già " + ":" + e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.error("Il file non è stato trovato " + ":" + e.getMessage(), e);
		}
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dataDictionary = dictionaryService;
	}

	public void setNameSpaceService(NamespaceService nameSpaceService) {
		this.nameSpace = nameSpaceService;
	}


}

