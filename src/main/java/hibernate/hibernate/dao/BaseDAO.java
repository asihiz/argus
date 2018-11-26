package hibernate.hibernate.dao;

import base.BaseTest;
import hibernate.hibernate.config.HibernateConnector;
import org.apache.log4j.Logger;
import org.hibernate.*;
import page_objects.LoginPage;
import util.GeneralUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by asih on 6/24/2017.
 */
public class BaseDAO {

    private Session session = null;
    private Query query = null;

    private static String path = null;
    private static Boolean isConf = false;

    private static final BaseDAO INSTANCE = new BaseDAO();
    private final static Logger logger = Logger.getLogger(BaseTest.class);


    public static BaseDAO getInstance () {
        return INSTANCE;
    }

    protected static Map<String, Object> params = new HashMap<>();

    protected void init() throws Exception {
        try {
            session = HibernateConnector.getSession();
            session.setCacheMode(CacheMode.IGNORE);
        } catch (Exception he){
            GeneralUtils.handleError("Error in hibernate ", he);
        }

    }

    protected <T> List<T> executeQuery(String queryString, Class<T> returnType, QueryType type) throws Exception {

        init();
        logger.info("Query : " + queryString);
        try {
            query = getQuery(type, queryString);
            setParams();
            List<T> queryList = query.list();
            if (queryList == null) {
                throw new NullPointerException();
            } else if (queryList.isEmpty())
                // log empty return list
                return queryList;
            else {
                return queryList;
            }
        } finally{
            params.clear();
            session.clear();
        }
    }

    protected void executeQuery(String queryString, QueryType type) throws Exception {

        init();
        logger.info("Query : " + queryString);
        try {
            query = getQuery(type, queryString);
            setParams();
            query.executeUpdate();

        } finally{
            clear();
        }
    }

    protected <T> void insert(T t) throws Exception {

        logger.info(t.toString());
        init();
         try {
             session.beginTransaction();
             session.save(t);
             session.getTransaction().commit();
         } catch (HibernateException he){
             GeneralUtils.handleError("Error in hibernate ", he);
         } finally {
             clear();
         }

    }

    private Query getQuery(QueryType type, String queryString){
        try {
            switch (type) {

                case HIBERNATE:
                    return session.createQuery(queryString);
                case SQL:
                    return session.createSQLQuery(queryString);
            }
        }
        catch (Exception e) {
            GeneralUtils.handleError("Couldn't create query");
        }
        return null;
    }

    private void setParams(){

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() instanceof String) {
                query.setParameter(entry.getKey(), entry.getValue());
            } else if(entry.getValue() instanceof Integer) {
                query.setInteger(entry.getKey(), (Integer) entry.getValue());
            } else if(entry.getValue() instanceof BigInteger) {
                query.setBigInteger(entry.getKey(), (BigInteger) entry.getValue());
            }
        }
    }

    public void clear(){
        session.setCacheMode(CacheMode.IGNORE);
        session.clear();
        params.clear();
    }

    public void closeSession(){
        session.close();
    }

    protected <E> Map<String, E> getParams() {
        return (Map<String, E>) params;
    }


    public enum QueryType{

        SQL, HIBERNATE;
    }
}
