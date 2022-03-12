package com.crio.jukebox.appConfig;

import com.crio.jukebox.repositories.*;
import com.crio.jukebox.services.*;
import com.crio.jukebox.commands.*;


public class ApplicationConfig {
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final IAlbumRepository albumRepository = new AlbumRepository();

    private final IPlaylistservice playlistService = new Playlistservice(userRepository,playlistRepository);
    private final IUserservice userService = new Userservice(userRepository, playlistRepository,songRepository);
    private final ISongservice songservice = new Songservice(songRepository);
    private final IAlbumservice albumservice = new Albumservice(albumRepository);
   // private final IContestService contestService = new ContestService(contestRepository, questionRepository, userRepository, userService);
    
    private final CreateUserCommand createuserCommand = new CreateUserCommand(userService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand deleteplaylistCommand = new DeletePlaylistCommand(playlistService);
    private final PlayPlaylistCommand playplaylistCommand = new PlayPlaylistCommand(userService);
    private final PlaySongCommand playsongCommand = new PlaySongCommand(userService);
    private final LoadDataCommand loaddataCommand = new LoadDataCommand(songservice,albumservice);
    private final DeleteSongCommand deletesongCommand = new DeleteSongCommand(playlistService);
    private final AddSongCommand addsongCommand = new AddSongCommand(playlistService);
    private final ModifyPlaylistCommand modifyplaylistCommand = new ModifyPlaylistCommand(playlistService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA", loaddataCommand);
        commandInvoker.register("CREATE-USER",createuserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deleteplaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",playplaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyplaylistCommand);
        commandInvoker.register("PLAY-SONG",playsongCommand);
        commandInvoker.register("ADD-SONG",addsongCommand);
        commandInvoker.register("DELETE-SONG",deletesongCommand);
        return commandInvoker;
    }


    
}