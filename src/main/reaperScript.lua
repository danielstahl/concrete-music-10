
-- https://github.com/EUGEN27771/ReaScripts/blob/master/Item%20Editing/gen_Explode%20multichannel%20Audio%20(Non-destructive).lua
-- https://www.reaper.fm/sdk/reascript/reascripthelp.html
-- https://www.youtube.com/watch?v=Ues74LMQkxs
function GetChannel(name)
  for i = 0, reaper.GetNumAudioInputs() - 1 do
    local channelName = reaper.GetInputChannelName(i)
    if channelName == name then return i
    end
  end
  return -1
end  

function CreateFolder(index, name)
  reaper.InsertTrackAtIndex(index, false)
  folder = reaper.GetTrack(0, index)
  reaper.GetSetMediaTrackInfo_String(folder, 'P_NAME', name, true)
  reaper.SetMediaTrackInfo_Value( folder, 'I_FOLDERDEPTH',1)
  reaper.SetMediaTrackInfo_Value(folder, 'I_FOLDERCOMPACT', 0)
end

function ImportAudio(index, channel, trackName, fileName, lastInFolder)
  local folderDepth = 0
  if lastInFolder then folderDepth = -1 end

  reaper.SetEditCurPos(0, false, false)
  reaper.InsertTrackAtIndex(index, false)
  tr = reaper.GetTrack(0, index)
  reaper.GetSetMediaTrackInfo_String(tr, 'P_NAME', trackName, true)
  reaper.SetMediaTrackInfo_Value( tr, 'I_FOLDERDEPTH',folderDepth)
  reaper.SetOnlyTrackSelected(tr, true)
  reaper.InsertMedia(fileName, 0)
  item = reaper.GetTrackMediaItem(tr, 0)
  take = reaper.GetActiveTake(item)
  reaper.SetMediaItemTakeInfo_Value(take, "I_CHANMODE", channel + 64 + 2)
end

CreateFolder(0, "Metal hit")
ImportAudio(1, 1, "Metal hit low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(2, 3, "Metal hit middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(3, 5, "Metal hit high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(4, "Metal harmonic")
ImportAudio(5, 7, "Metal harmonic low 1", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(6, 9, "Metal harmonic low 2", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(7, 11, "Metal harmonic middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(8, 13, "Metal harmonic high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(9, "Metal scratch")
ImportAudio(10, 15, "Metal scratch low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(11, 17, "Metal scratch middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(12, 19, "Metal scratch high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(13, "Stone hit")
ImportAudio(14, 21, "Stone hit low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(15, 23, "Stone hit middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(16, 25, "Stone hit high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(17, "Stone harmonic")
ImportAudio(18, 27, "Stone harmonic low 1", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(19, 29, "Stone harmonic low 2", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(20, 31, "Stone harmonic middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(21, 33, "Stone harmonic high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(22, "Stone scratch")
ImportAudio(23, 35, "Stone scratch low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(24, 37, "Stone scratch middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(25, 39, "Stone scratch high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(26, "Wood hit")
ImportAudio(27, 41, "Wood hit low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(28, 43, "Wood hit middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(29, 45, "Wood hit high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(30, "Wood harmonic")
ImportAudio(31, 47, "Wood harmonic low 1", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(32, 49, "Wood harmonic low 2", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(33, 51, "Wood harmonic middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(34, 53, "Wood harmonic high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)

CreateFolder(35, "Wood scratch")
ImportAudio(36, 55, "Wood scratch low", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", false)
ImportAudio(37, 57, "Wood scratch middle", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
ImportAudio(38, 59, "Wood scratch high", "/Users/danielstahl/Documents/Music/Pieces/Concrete Music/Concrete Music 10/stage/concreteMusic10Score.caf", true)
