package onl.netfishers.netshot.device.attribute;

import javax.xml.bind.annotation.XmlElement;

import org.graalvm.polyglot.Value;

import onl.netfishers.netshot.device.script.helper.JsDeviceHelper;

public class AttributeDefinition {
	
	static public enum AttributeLevel {
		DEVICE,
		CONFIG
	}
	
	static public enum AttributeType {
		NUMERIC,
		TEXT,
		LONGTEXT,
		DATE,
		BINARY,
		BINARYFILE
	}
	
	private AttributeType type;
	private AttributeLevel level;
	private String name;
	private String title;
	private boolean comparable = false;
	private boolean searchable = false;
	private boolean checkable = false;
	private boolean dump = false;
	private String preDump = null;
	private String postDump = null;
	private String preLineDump = null;
	private String postLineDump = null;
	
	protected AttributeDefinition() {
		
	}

	public AttributeDefinition(AttributeType type, AttributeLevel level, String name,
			String title, boolean comparable, boolean searchable, boolean checkable) {
		super();
		this.type = type;
		this.level = level;
		this.name = name;
		this.title = title;
		this.comparable = comparable;
		this.searchable = searchable;
		this.checkable = checkable;
	}
	
	public AttributeDefinition(AttributeLevel level, String name, Value data) throws Exception {
		this.level = level;
		this.name = name;
		this.title = data.getMember("title").asString();
		if (!this.title.matches("^[0-9A-Za-z\\-_\\(\\)][0-9A-Za-z \\-_\\(\\)]+$")) {
			throw new IllegalArgumentException("Invalid title for item %s.");
		}
		String type = data.getMember("type").asString();
		if (type.equals("Text")) {
			this.type = AttributeType.TEXT;
		}
		else if (type.equals("LongText")) {
			this.type = AttributeType.LONGTEXT;
		}
		else if (type.equals("Numeric")) {
			this.type = AttributeType.NUMERIC;
		}
		else if (type.equals("Binary")) {
			this.type = AttributeType.BINARY;
		}
		else if (type.equals("BinaryFile")) {
			this.type = AttributeType.BINARYFILE;
		}
		else {
			throw new IllegalArgumentException("Invalid type for item %s.");
		}
		this.searchable = JsDeviceHelper.getBooleanMember(data, "searchable", false);
		this.comparable = JsDeviceHelper.getBooleanMember(data, "comparable", false);
		this.checkable = JsDeviceHelper.getBooleanMember(data, "checkable", false);
		Value dump = data.getMember("dump");
		if (dump == null) {
			this.dump = false;
		}
		else {
			if (dump.isBoolean()) {
				this.dump = dump.asBoolean();
			}
			else {
				this.dump = true;
				if (dump.hasMember("pre")) {
					this.preDump = dump.getMember("pre").asString();
				}
				if (dump.hasMember("preLine")) {
					this.preLineDump = dump.getMember("preLine").asString();
				}
				if (dump.hasMember("post")) {
					this.postDump = dump.getMember("post").asString();
				}
				if (dump.hasMember("postLine")) {
					this.postLineDump = dump.getMember("postLine").asString();
				}
				
			}
		}
	}

	@XmlElement
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	@XmlElement
	public AttributeLevel getLevel() {
		return level;
	}
	public void setLevel(AttributeLevel level) {
		this.level = level;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public boolean isComparable() {
		return comparable;
	}
	public void setComparable(boolean comparable) {
		this.comparable = comparable;
	}
	@XmlElement
	public boolean isCheckable() {
		return checkable;
	}
	public void setCheckable(boolean checkable) {
		this.checkable = checkable;
	}
	@XmlElement
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isDump() {
		return dump;
	}
	public void setDump(boolean dump) {
		this.dump = dump;
	}
	public String getPreDump() {
		return preDump;
	}
	public void setPreDump(String preDump) {
		this.preDump = preDump;
	}
	public String getPostDump() {
		return postDump;
	}
	public void setPostDump(String postDump) {
		this.postDump = postDump;
	}
	public String getPreLineDump() {
		return preLineDump;
	}
	public void setPreLineDump(String preLineDump) {
		this.preLineDump = preLineDump;
	}
	public String getPostLineDump() {
		return postLineDump;
	}
	public void setPostLineDump(String postLineDump) {
		this.postLineDump = postLineDump;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttributeDefinition [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (level != null) {
			builder.append("level=");
			builder.append(level);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		builder.append("comparable=");
		builder.append(comparable);
		builder.append(", searchable=");
		builder.append(searchable);
		builder.append("]");
		return builder.toString();
	}
}