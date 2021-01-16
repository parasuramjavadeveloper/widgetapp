package com.example.apipoc.repositories;

import com.example.apipoc.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Parasuram
 */
@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {

   @Query("from Widget w order by w.zIndex ")
   public List<Widget> findAllOrderByZIndexAsc();
}
