package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.DB.JPAUtil;
import lk.ijse.dep.rcrmoto.business.custom.LoginBO;
import lk.ijse.dep.rcrmoto.dao.DAOFactory;
import lk.ijse.dep.rcrmoto.dao.DAOTypes;
import lk.ijse.dep.rcrmoto.dao.custom.AdminDAO;
import lk.ijse.dep.rcrmoto.dto.LoginDTO;
import lk.ijse.dep.rcrmoto.entity.Admin;

import javax.persistence.EntityManager;


public class LoginBOImpl implements LoginBO {
    AdminDAO adminDAO=DAOFactory.getInstance().getDAO(DAOTypes.ADMIN);

    @Override
    public boolean authentication(LoginDTO loginDTO) throws Exception {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        adminDAO.setEntityManager(em);
        em.getTransaction().begin();
            boolean authentication = adminDAO.authentication(new Admin(loginDTO.getUsename(), loginDTO.getPassword()));

        em.getTransaction().commit();
        em.close();
        return authentication;
    }

}
