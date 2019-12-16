package pl.pas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pas.pas.model.Firms.Firm;
import pl.pas.pas.repo.IRepo;

import java.util.List;

@Service
public class FirmService {

    private IRepo firmRepo;

    @Autowired
    public FirmService(IRepo firmRepo) {
        this.firmRepo = firmRepo;
    }

    public List<Firm> getFirms(){
        return firmRepo.getAll();
    }
}
