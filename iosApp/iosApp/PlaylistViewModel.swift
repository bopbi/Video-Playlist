//
//  PlaylistViewModel.swift
//  iosApp
//
//  Created by Bobby Prabowo on 21/02/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

final class PlaylistViewModel: ObservableObject {
    
    @Published var contents: [Content] = Array()
    var fetchPlaylist: InitialFetchPlaylist?
    var loadPlaylist: InitialLoadPlaylist?
    
    init() {
        let videoAPi = VideoApi()
        let database = Platform.init().database
        let repository = ContentRepositoryImpl(videoApi: videoAPi, database: database)
        self.fetchPlaylist = InitialFetchPlaylistImpl(contentRepository: repository)
        self.loadPlaylist = InitialLoadPlaylistImpl(repository: repository)
    }
    
    func performLoadAndFetch() {
        self.fetchPlaylist?.execute().collect(collector: Collector<UseCaseResult<NSArray>> { result in
            if (result.isSuccess() && (result.value is [Content])) {
                self.contents = result.value as! [Content]
            } else {
                
            }
        }, completionHandler: { (unit, error) in
            
        })
        
        self.loadPlaylist?.execute().collect(collector: Collector<UseCaseResult<NSArray>> { result in
            if (result.isSuccess() && (result.value is [Content])) {
                self.contents = result.value as! [Content]
            } else {
                
            }
        }, completionHandler: { (unit, error) in
            
        })
    }
    
}

extension Content : Identifiable { }
