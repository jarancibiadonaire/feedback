package cl.uchile.dcc.feedback.mappers;

import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.model.LocationVO;

public class LocationMapper implements Mapper<Location, LocationVO> {

	@Override
	public LocationVO getSummary(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocationVO getBasic(Location entity) {
		if(entity==null)
			return null;
		LocationVO vo=new LocationVO();
		vo.setId(entity.getId());
		vo.setAddress(entity.getAddress());
		vo.setLat(entity.getLat());
		vo.setLng(entity.getLng());
		if(entity.getComuna()!=null){
			vo.setComuna(entity.getComuna().getName());
			vo.setRegion(entity.getComuna().getRegion().getName());
		}
		return vo;
	}

	@Override
	public LocationVO getData(Location entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
