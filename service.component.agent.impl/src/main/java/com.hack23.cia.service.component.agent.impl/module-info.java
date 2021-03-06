module com.hack23.cia.service.component.agent.impl {


	exports com.hack23.cia.service.component.agent.impl.command;
	exports com.hack23.cia.service.component.agent.impl.riksdagen;
	exports com.hack23.cia.service.component.agent.impl.val;
	exports com.hack23.cia.service.component.agent.impl.worldbank;

	opens com.hack23.cia.service.component.agent.impl.command to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	
	opens com.hack23.cia.service.component.agent.impl.riksdagen to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.riksdagen.workers to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.riksdagen.workers.data to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.data to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	
	opens com.hack23.cia.service.component.agent.impl.val to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	
	opens com.hack23.cia.service.component.agent.impl.worldbank to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.worldbank.workers to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.worldbank.workers.data to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.worldbank.workgenerator to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	opens com.hack23.cia.service.component.agent.impl.worldbank.workgenerator.data to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
	
	opens com.hack23.cia.service.component.agent.impl.common.jms to spring.aop, spring.core, spring.beans, spring.context, spring.context.support;
		
	requires java.xml.bind;
	requires java.annotation;
	requires org.slf4j;
	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.core;
	requires spring.jms;
	requires org.apache.commons.lang3;

	requires spring.security.core;

	requires org.joda.time;

	requires javax.jms;
	
	requires com.hack23.cia.service.external.common;
    requires com.hack23.cia.service.data.api;
	requires com.hack23.cia.service.component.agent.api;
	requires com.hack23.cia.service.external.riksdagen;
	requires com.hack23.cia.service.external.val;
	requires com.hack23.cia.service.external.worldbank;

	requires com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.voteringlista.impl;
	requires com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires com.hack23.cia.model.external.worldbank.topic.impl;
	requires com.hack23.cia.model.external.worldbank.indicators.impl;
	requires com.hack23.cia.model.external.riksdagen.documentcontent.impl;
	requires com.hack23.cia.model.external.worldbank.data.impl;
	requires com.hack23.cia.model.external.riksdagen.person.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentstatus.impl;
	requires com.hack23.cia.model.external.riksdagen.dokumentlista.impl;
	requires com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	requires com.hack23.cia.model.external.val.partier.impl;
	requires com.hack23.cia.model.external.riksdagen.personlista.impl;
	requires com.hack23.cia.model.external.val.landstingvalkrets.impl;
	requires com.hack23.cia.model.external.riksdagen.votering.impl;
}