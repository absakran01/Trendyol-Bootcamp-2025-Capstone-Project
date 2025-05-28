    package com.absakran.capstone_project.repositories;

    import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

        @Override
        @Transactional
        public void buyProduct(Product product, int quantity) {
            // TODO Auto-generated method stub
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null");
            }
            Product temp = getProductById(product.getId());
            temp.setStock(temp.getStock()-quantity);
            em.persist(temp);
        }

        @Override
        public Product getProductById(long id) {
            // TODO Auto-generated method stub
            if(id==0){
                throw new IllegalArgumentException("Id cannot be null");
            }
            return em.find(Product.class, id);
        }

        @Override
        public List<Product> getProductsByKeyword(String keyword) {
            // TODO Auto-generated method stub
            if(keyword==null){
                throw new IllegalArgumentException("Keyword cannot be null");
            }
            return em.
            createQuery(
                    "FROM Product p WHERE LOWER(p.name) LIKE :kw OR LOWER(p.description) LIKE :kw",
                    Product.class
                )
                 .setParameter("kw", "%"+ keyword+ "%")
                 .getResultList();
        }

        @Override
        @Transactional
        public void restockProduct(Product product, int restock) {
            // TODO Auto-generated method stub
            if(product==null){
                throw new IllegalArgumentException("Product cannot be null");
            }

            Product temp = em.find(Product.class, product.getId());
            temp.setStock(temp.getStock()+restock);
            em.persist(temp);

        }

        


        // DI 
        public ProductDAOImpl(EntityManager em){
            this.em = em;
            System.out.println("ProductDAOImpl constructor called");
        }

    }