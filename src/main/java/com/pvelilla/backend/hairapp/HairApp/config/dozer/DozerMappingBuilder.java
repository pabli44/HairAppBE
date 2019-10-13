package com.pvelilla.backend.hairapp.HairApp.config.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;

//import com.pvelilla.backend.hairapp.HairApp.domain.SettingDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
//import com.pvelilla.backend.hairapp.HairApp.entities.Setting;
import com.pvelilla.backend.hairapp.HairApp.entities.User;

public class DozerMappingBuilder {

	private DozerBeanMapper dozerBeanMapper;

	public DozerMappingBuilder() {
		this.dozerBeanMapper = dozerBeanMapper();
	}

	private DozerBeanMapper dozerBeanMapper() {
		List<String> mappingFiles = new ArrayList<>();
		mappingFiles.add("dozerJdk8Converters.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappingFiles);
		mapper.addMapping(beanMappingBuilder());
		return mapper;
	}

	public <T> T map(Object source, Class<T> destinationClass) {
		return dozerBeanMapper.map(source, destinationClass);
	}

	private BeanMappingBuilder beanMappingBuilder() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				//mapping(SettingDTO.class, Setting.class, TypeMappingOptions.wildcard(true))
				mapping(UserDTO.class, User.class, TypeMappingOptions.wildcard(true))
						.fields("tableName", "settingPK.tableName", FieldsMappingOptions.copyByReference())
						.fields("orderPosition", "settingPK.orderPosition", FieldsMappingOptions.copyByReference())
						.fields("fieldName", "settingPK.fieldName", FieldsMappingOptions.copyByReference());
			}
		};
	}

}
