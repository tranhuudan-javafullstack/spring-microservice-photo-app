/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.huudan.photoapp.api.albums.io.controllers;

import vn.com.huudan.photoapp.api.albums.data.AlbumEntity;
import vn.com.huudan.photoapp.api.albums.service.AlbumsService;
import vn.com.huudan.photoapp.api.albums.ui.model.AlbumResponseModel;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

import org.modelmapper.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {

    private final AlbumsService albumsService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if (albumsEntities == null || albumsEntities.isEmpty())
            return returnValue;

        Type listType = new TypeToken<List<AlbumResponseModel>>() {
        }.getType();

        returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }
}
