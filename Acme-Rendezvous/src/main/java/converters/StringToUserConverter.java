package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.User;

@Component
@Transactional
public class StringToUserConverter implements Converter<String, User>{
	
	@Autowired
	//private UserRepository	userRepository;


	@Override
	public User convert(String text) {

		User result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				//result = this.userRepository.findOne(id);

			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		//return result;
		return null;
	}

}
