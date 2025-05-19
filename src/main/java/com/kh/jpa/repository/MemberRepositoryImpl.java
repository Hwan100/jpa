package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // 해당 클래스는 DB와 관련된 작업을 수행하는 클래스다.
public class MemberRepositoryImpl implements MemberRepository{

    @PersistenceContext // EntityManager를 주입해줘.
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findOne(String userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }

    @Override
    public List<Member> findAll() {
        //JPQL : 엔티티기반 쿼리를 전달하는 방법
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public List<Member> findByName(String name) {
        String query = "select m from Member m where m.userName like :name";
        return em.createQuery(query, Member.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
