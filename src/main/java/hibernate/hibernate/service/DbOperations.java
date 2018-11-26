package hibernate.hibernate.service;


import hibernate.hibernate.dao.RepositoriesDAO;
import hibernate.hibernate.pojo.Repositories;

import java.math.BigInteger;
import java.util.List;


public class DbOperations {

    private RepositoriesDAO repositoriesDAO = new RepositoriesDAO();

    private RepositoriesOperations repositoriesOperations = this.new RepositoriesOperations();

    public DbOperations(){
    }

    public class RepositoriesOperations {

        public void insert(Repositories repositories) throws Exception {
            repositoriesDAO.insert(repositories);
        }

        public RepositoriesOperations getRepositoriesOperations() {
            return repositoriesOperations;
        }

    }

    public RepositoriesOperations getRepositoriesOperations() {
        return repositoriesOperations;
    }



}
