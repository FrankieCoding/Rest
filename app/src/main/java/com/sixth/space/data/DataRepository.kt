package com.sixth.space.data


import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {
    suspend override fun fetchHotList(str: String): Flow<Resource<List<VideoInfo>>> {
        return flow {
            emit(remoteRepository.fetchHotList(str))
        }.flowOn(ioDispatcher);
    }

    suspend override fun fetchReplyList(id: String): Flow<Resource<List<VideoInfo>>> {
        return flow {
            emit(remoteRepository.fetchReplyList(id))
        }.flowOn(ioDispatcher);
    }

    suspend override fun fetchRecommend(id: String): Flow<Resource<List<VideoInfo>>> {
        return flow {
            emit(remoteRepository.fetchRecommend(id))
        }.flowOn(ioDispatcher);
    }

    suspend override fun fetchHomeData(
        date: String,
        num: String
    ): Flow<Resource<List<VideoInfo>>> {
        return flow {
            emit(remoteRepository.fetchHomeData(date, num))
        }.flowOn(ioDispatcher);
    }


}