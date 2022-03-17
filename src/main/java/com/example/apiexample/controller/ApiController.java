package com.example.apiexample.controller;

import java.util.List;
import java.util.Map;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.model.Search;
import com.example.apiexample.model.Track;
import com.example.apiexample.model.User;
import com.example.apiexample.services.ExternalApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ApiController {
    ExternalApiService externalApiController = new ExternalApiService();
    
    @Autowired
    private AtivoController ativoController;

    @Autowired
    private TrackController trackController;

    @Autowired
    private SearchController searchController;

    @Autowired
    private UserController userController;

    @PostMapping("/index")
    public List<Search> index(@RequestBody Map<String, String> request){

        User user = new User(request.get("email"),request.get("password"));

        User userDB = userController.getUserByEmail(user);

        if (userDB == null){

            userDB = userController.addUser(user);
            
            Ativo ativo = new Ativo(request.get("term"),request.get("domain"), userDB);

            Track track = new Track();

            ativo.setTrack(track);

            track.setAtivo(ativo);

            Ativo ativoDB = ativoController.addAtivo(ativo);

            List<Search> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

            Track trackDB = trackController.getTrack(ativoDB.getTrack().getId());

            for (int i = 0; i < externalApiResult.size(); i++) {
                
                Search item = externalApiResult.get(i);
                            
                item.setTrack(trackDB);
                            
                searchController.addSearch(item);
            }

            List<Search> results = searchController.getAllSearchByTrack(trackDB.getId());

            return results;
            
        } else {

            if (userDB.getPassword().equals(user.getPassword())){

                List<Ativo> ativosDB = ativoController.getAllAtivosByIdUser_id(userDB.getId());

                Ativo ativo = new Ativo();

                if (ativosDB.size() != 0) {

                    for (int i = 0; i < ativosDB.size(); i++) {

                        String ativoTest = ativosDB.get(i).getEmail();
                        String termTest = request.get("term");
                        if (ativoTest.equals(termTest)){
    
                            ativo = ativosDB.get(i);
    
                            break;
    
                        }
    
                    }

                }

                if (ativo.getId() == null) {

                    Track track = new Track();

                    ativo.setEmail(request.get("term"));

                    ativo.setDomain(request.get("domain"));

                    ativo.setUser(userDB);

                    ativo.setTrack(track);

                    track.setAtivo(ativo);

                    ativo = ativoController.addAtivo(ativo);

                    List<Track> tracksDB = trackController.getAllTrackByAtivo_id(ativo.getId());
                    Track trackDB = tracksDB.get(0);

                    List<Search> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

                    for (int i = 0; i < externalApiResult.size(); i++) {
                        
                        Search item = externalApiResult.get(i);
                                    
                        item.setTrack(trackDB);
                                    
                        searchController.addSearch(item);
                    }

                } else {

                    List<Track> tracksDB = trackController.getAllTrackByAtivo_id(ativo.getId());
                    Track trackDB = tracksDB.get(0);

                    List<Search> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

                    for (int i = 0; i < externalApiResult.size(); i++) {
                        
                        Search item = externalApiResult.get(i);
                                    
                        item.setTrack(trackDB);
                                    
                        searchController.addSearch(item);
                    }

                    
                    return externalApiResult;

                }

            } else {

                return null;
            }

        }

        return null;
    
    }
}