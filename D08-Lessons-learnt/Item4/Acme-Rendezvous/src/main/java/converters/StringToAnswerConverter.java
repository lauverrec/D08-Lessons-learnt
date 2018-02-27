package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AnswerRepository;
import domain.Answer;

@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer>{
	
	@Autowired
	private AnswerRepository	answerRepository;


	@Override
	public Answer convert(String text) {

		Answer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.answerRepository.findOne(id);

			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
