package com.liuyihui.lrn.mapper.interf;

import java.util.List;

import com.liuyihui.lrn.annotation.MybatisRepository;
import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.entity.Entity;

/**
 * @author liuyihui
 * AdminMapper½Ó¿Ú
 *
 */
@MybatisRepository
public interface AdminMapper {
	public void addAdmin(Admin admin);
	public void updateAdmin(Admin admin);
	public void deleteById(int id);
	public Admin findById(int id);
	public List<Admin> findAll();
	public List<Entity> findAllentity();
}
