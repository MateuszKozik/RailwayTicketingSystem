package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.ZakupBiletu;
import com.kozik.PKPTicket.repositories.ZakupBiletuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZakupBiletuService {
    @Autowired
    private ZakupBiletuRepository zakupBiletuRepository;
    
    public List<ZakupBiletu> listAll(){
        return zakupBiletuRepository.findAll();
    }
    
    public void save(ZakupBiletu zakupBiletu){
        zakupBiletuRepository.save(zakupBiletu);
    }
    
    public ZakupBiletu get(long id){
        return zakupBiletuRepository.findById(id).get();
    }
    
    public void delete(long id){
        zakupBiletuRepository.deleteById(id);
    }
    
}
