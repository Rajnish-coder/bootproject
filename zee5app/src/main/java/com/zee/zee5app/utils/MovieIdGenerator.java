package com.zee.zee5app.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public class MovieIdGenerator extends SequenceStyleGenerator {

	@Override
	// used to generate customized id
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
		Movie movie = (Movie)object;
		//1. we need connection object
		Connection connection = session.connection();
		//2. perform insert operation on unseridgeneratortable
		//3. we need to perform the concatenation
		//4. we need to update the id
		//5. return the newly created id
		
		PreparedStatement preparedStatement = null;
		String query = "select id from movieidgenerator";
		String updatequery = "update movieidgenerator set id=? where id=?";
		ResultSet resultSet = null;
		int old_id;
		// connection object
		
		try {
			// get old id from useridgenerator table and increment the id and append it for userid
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next() == true) {
				int num = resultSet.getInt(1);
				old_id = num;
				num++;
				int length_of_id = (int)(Math.log10(num)+1);
				String movieid = movie.getMovieName().charAt(0)+""+movie.getMovieName().charAt(1)+"0".repeat(10-2-length_of_id)+num;
				
				// update the incremented id to useridgenerator table
				preparedStatement = connection.prepareStatement(updatequery);
				preparedStatement.setInt(1, num);
				preparedStatement.setInt(2, old_id);
				int res = preparedStatement.executeUpdate();
				if(res == 0)
					throw new UnableToGenerateIdException("Unable to generate id");
				
				return movieid;
			}
		}catch (SQLException | UnableToGenerateIdException e) {
			e.printStackTrace();
			System.out.println(e);
		//	throw new UnableToGenerateIdException("Unable to generate id due to "+e.getMessage());
		}
		return null;
		
		//return super.generate(session, object);
	}
}
