module com.hack23.cia.service.external.riksdagen {
	exports com.hack23.cia.service.external.riksdagen.api;
	exports com.hack23.cia.service.external.riksdagen.impl;
	
	opens com.hack23.cia.service.external.riksdagen.api to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.external.riksdagen.impl to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;

	requires java.xml.bind;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;


	requires com.hack23.cia.service.external.common;

	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;

}