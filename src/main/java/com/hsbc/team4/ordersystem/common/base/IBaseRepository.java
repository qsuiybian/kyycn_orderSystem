package com.hsbc.team4.ordersystem.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 *
 * @author chenRenXun
 * @date 2018/4/18 0018
 * 扩展JPA接口
 */
@NoRepositoryBean
public interface IBaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * 通过Id查询
     * @param id entity ID
     * @return T
     */
    @Query(value = "SELECT * FROM #{#entityName} t  where t.id=?1",nativeQuery=true)
    T findByEntityId(String id) ;

    /**
     * 分页信息
     * @param status 状态
     * @param pageable 分页信息
     * @return Page<T>
     */
    Page<T> findByStatus(int status, Pageable pageable);

    /**
     * 软删除
     * @param id 实体ID
     * @param status 状态
     * @return int
     */
    @Query(value = "update #{#entityName} t set t.status=?2 where t.id=?1",nativeQuery=true)
    @Modifying
    @Transactional
    int updateStatusById(String id, int status);




}
