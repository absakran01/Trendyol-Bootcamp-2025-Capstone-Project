    package com.absakran.capstone_project.repositories;

    import java.util.List;

    import org.springframework.stereotype.Repository;

    import com.absakran.capstone_project.entities.Product;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityManagerFactory;
    import jakarta.persistence.Persistence;
    import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

    @Repository
    public class ProductDAOImpl implements ProductDAO{

        private EntityManager em;

        @Override
        public List<Product> getAllProducts() {
            // TODO Auto-generated method stub
            List<Product> result = null;
            try {
                result = em
                .createQuery("From Product", Product.class)
                .getResultList();
            } catch (Exception e) {
                throw e;
            }
            em.close();
            return result;
        }

        @Override
        @Transactional
        public void addProduct(Product product) {
            // TODO: process POST request
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null");
            }
            em.persist(product);
        }

        public ProductDAOImpl(EntityManager em){
            this.em = em;
            System.out.println("ProductDAOImpl constructor called");
        }

        

    }