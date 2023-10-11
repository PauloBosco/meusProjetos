
const clientId = "2f50ebf698c14047ae964a5836903cf2";
const clientSecret = "3743a18d1a7a437ea2d56d58b8d061b2";


const getToken = async ()=>{
    const result = await fetch("https://accounts.spotify.com/api/token",{
        method: "POST",
        headers:{
            "content-type": "application/x-www-form-urlencoded",
            "authorization": 'basic ' +btoa(clientId+':'+clientSecret)
        },
        body:"grant_type=client_credentials"
    });

    const data = await result.json();
    return data.access_token;
};

const getGenres = async(token) =>{
    const result = await fetch('https://api.spotify.com/v1/browse/categories?locale=pt_BR',
    {
        method: "GET",
        headers: {"Authorization":"Bearer "+token }

    })
    const data = await result.json();
    return data.categories;
};

const getPlayListByGenre = async (token, genreId) =>{
    const limit = 10;
    
    const result = await fetch(`https://api.spotify.com/v1/browse/categories/${genreId}/playlists?limit=${limit}`,
    {
        method: "GET",
        headers: {"Authorization":"Bearer "+token }

    });
    const data = await result.json();
    return data.playlists;

}

const getPlayListTracks = async (token, playListId) => {
    
    const result = await fetch(`https://api.spotify.com/v1/playlists/${playListId}`,
    {
        method: "GET",
        headers: {"Authorization":"Bearer "+token }

    });

    const data = await result.json();
    return data.tracks;
}

const getUser = async (token, userName) =>{
    
    const result = await fetch(`https://api.spotify.com/v1/users/${userName}`,
    {
        headers:{"Authorization": "Bearer " + token}
    });

    const data = await result.json();
    return data;
}

const getUserPlaylists = async (token, userName) =>{
    
    const result = await fetch(`https://api.spotify.com/v1/users/${userName}/playlists`,
    {
        headers:{"Authorization": "Bearer " + token}
    });

    const data = await result.json();
    return data;
}


(async ()=>{
    const token = await getToken();
    const generos = await getGenres(token);
    const genreId = generos.items[0].id;
    const playlist = await getPlayListByGenre(token, genreId);
    const playlistId = playlist.items[0].id;
    const tracks = await getPlayListTracks(token, playlistId);
    const usuario = await getUser(token, "caroltanag");
    const usuarioPlaylists = await getUserPlaylists(token, "caroltanag");
    for (const playlistItem of usuarioPlaylists.items) {
        let count = 0;
        const yellowColor = "\x1b[33m"
        const resetColor = "\x1b[0m";
        const iddd = playlistItem.id;
        console.log(iddd);
        console.log(` #################### PlayList: ${playlistItem.name} #################### `);
        const todasTracks = await getPlayListTracks(token, iddd);
        todasTracks.items.forEach(element => {
            count++;
            console.log(element.track.name);
        });
        console.log(`Total Músicas: ${yellowColor + count + resetColor}`);
        console.log("");
        
    }   
})();

