import java.sql.ResultSet;

//  T => Generic
public interface IResultMapper<T> {
	T resultMapping(ResultSet rs);
}